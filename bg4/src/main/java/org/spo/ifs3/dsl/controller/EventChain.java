package org.spo.ifs3.dsl.controller;

import java.util.ArrayList;
import java.util.List;

import org.spo.ifs3.dsl.model.AbstractTask;

public class EventChain {

	List<Object> eventChain = new ArrayList<Object>();



	public EventChain addEvent(StateInfo event){
		if(!(eventChain.get(eventChain.size()-1) instanceof AbstractTask)){
			throw new DSLException();
		}
		eventChain.add(event);
		return this;

	}
	public EventChain addState(AbstractTask task){		 
		eventChain.add(task);
		return this;

	}

	public EventChain setGroundState(AbstractTask task){
		eventChain.add(task);
		return this;

	}

	public AbstractTask getByEvent(StateInfo event){
		return (AbstractTask)eventChain.get(eventChain.indexOf(event)+1);
	}
	
	public AbstractTask getRootTask(){
		return (AbstractTask)eventChain.get(0);
	}
}
