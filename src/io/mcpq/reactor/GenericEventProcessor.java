package io.mcpq.reactor;



import org.springframework.stereotype.Service;

import io.mcpq.bo.GenericBO;
import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class GenericEventProcessor implements Consumer<Event<GenericBO>>{

	@Override
	public void accept(Event<GenericBO> genericEvent) {
		
		GenericBO bo = genericEvent.getData();
		System.out.println("++++++++++++++++++++++");
		System.out.println("PROCESSING:"+bo.getId());
		System.out.println("++++++++++++++++++++++");
		
	}

}
