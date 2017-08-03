package io.mcpq.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.mcpq.reactor.GenericEventProcessor;
import io.mcpq.reactor.PersistEventProcessor;
import reactor.Environment;
import reactor.bus.EventBus;

@SpringBootApplication
@ComponentScan(basePackages = { "io.mcpq.rest", "io.mcpq.reactor" })
public class SpringBootWebApplication extends SpringBootServletInitializer  {

	@Bean
	public reactor.Environment env() {
		return new reactor.Environment();
	}

	@Bean
	EventBus createEventBus(Environment env) {
		return EventBus.create(env, Environment.THREAD_POOL);
	}

	@Autowired
	private EventBus eventBus;

	@Autowired
	private GenericEventProcessor genericEventProcessor;

	@Autowired
	private PersistEventProcessor persistEventProcessor;

	
	
	@PostConstruct
	public void onStartUp() {
		System.out.println("Initializing Bus");
		eventBus.on(reactor.bus.selector.Selectors.$("notificationConsumer"), genericEventProcessor);
		eventBus.on(reactor.bus.selector.Selectors.R("(.+)"), persistEventProcessor);
	}

	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}



}