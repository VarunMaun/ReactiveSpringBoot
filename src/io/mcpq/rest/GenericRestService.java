package io.mcpq.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.mcpq.bo.GenericBO;
import reactor.bus.Event;
import reactor.bus.EventBus;

@RestController
@RequestMapping("/rest")
public class GenericRestService {
	
	 @Autowired
	 private EventBus eventBus;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/hola", produces = "text/plain")
	public String welcome() {
		
		
		GenericBO bo = new GenericBO();
		bo.setId(new Date().toString());
		System.out.println("Sending event:"+bo.getId());
		eventBus.notify("notificationConsumer", Event.wrap(bo));
		

		return "popatlal";
	}


}