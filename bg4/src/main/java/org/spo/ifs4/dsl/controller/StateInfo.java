package org.spo.ifs4.dsl.controller;

public class StateInfo {

	public DSLConstants.EventType eventType;
	public String trxId;//target trx
	public String eventId;//target event mostly for triggering an event from the page.
	public String taskId;//target task
	public String dataId;//data key for the trx
	public NavEvent lastEvent;
	
	
	public StateInfo(DSLConstants.EventType eventType, String trxId,String taskId,String eventId,  String dataId){
	this.trxId=trxId;
	this.taskId=taskId;
	this.eventId=eventId;
	this.dataId=dataId;
	}

	
	public StateInfo(){
		
	}
	
	public StateInfo(DSLConstants.EventType type, String taskId){
		if(!type.equals(DSLConstants.EventType.REFRESHPAGE)){
			throw new DSLException();
		}		
		//this.previousEvent=this.copy();
		this.taskId=taskId;		
		}
	
	public String getRedirect(){return "";}
	
	
}
