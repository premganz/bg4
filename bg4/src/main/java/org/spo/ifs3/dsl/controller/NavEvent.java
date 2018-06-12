package org.spo.ifs3.dsl.controller;

import org.spo.ifs3.dsl.controller.DSLConstants.EventType;

public class NavEvent {

	private DSLConstants.EventType eventType;
	private String trxId;//target trx
	private String eventId;//target event mostly for triggering an event from the page.
	private String taskId;//target task
	public String dataId;//data key for the trx
	public NavEvent previousEvent;
	public String url;

	public String getTaskId(){
		return taskId;
	}
	public EventType getEventType(){
		return this.eventType;
	}
	public String getEventId(){
		return this.eventId;
	}
	public String getTrxId(){
		return this.trxId;
	}
	public NavEvent(DSLConstants.EventType eventType, String trxId,String taskId,String eventId,  String dataId){
		this.eventType=eventType;
		this.trxId=trxId;
		this.taskId=taskId;
		this.eventId=eventId;
		this.dataId=dataId;
	}
	public NavEvent(DSLConstants.EventType eventType){
		this.eventType=eventType;
	}
	
	public static NavEvent create(DSLConstants.EventType type, String param){
		NavEvent event = new NavEvent(type);
		if((type.equals(DSLConstants.EventType.REFRESHPAGE)||type.equals(DSLConstants.EventType.TASKSET))){
			event.taskId=param;	
		}else if(type.equals(DSLConstants.EventType.PROCESSEVENT)){
			event.eventId=param;
		}else{
			event.trxId=param;
		}
		return event;		
	}
	
public static NavEvent blend(NavEvent eventIn, NavEvent event){
		
		
		if(eventIn!=null){
			if(event.trxId==null)event.trxId=eventIn.trxId;
			if(event.dataId==null)event.dataId=eventIn.dataId;
			if(event.eventId==null)event.eventId=eventIn.eventId;
			if(event.taskId==null)event.taskId=eventIn.taskId;
						
			}
		
		return event;		
	}
	public static NavEvent create(DSLConstants.EventType type){
		NavEvent event = new NavEvent(type);
		return event;		
	}
	public String getRedirect(){return "";}

	
	@Override
	public String toString() {
		StringBuffer identifier= new StringBuffer();
		identifier.append(eventType);
		if((eventType.equals(DSLConstants.EventType.REFRESHPAGE)||eventType.equals(DSLConstants.EventType.TASKSET))){
			identifier.append("->"+taskId);	
		}else if(eventType.equals(DSLConstants.EventType.PROCESSEVENT)){
			identifier.append("->"+eventId);
		}else{
			identifier.append("->"+trxId);
		}
		return identifier.toString()+super.toString();
	}
	

}
