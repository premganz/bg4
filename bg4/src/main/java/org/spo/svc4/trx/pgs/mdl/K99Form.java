package org.spo.svc4.trx.pgs.mdl;

import java.util.ArrayList;
import java.util.List;

public class K99Form {
	private String mailId;
	private String runningTextMessage;
	private String date;
	private String smallMessage;
	private String nameField;
	private String messageField;
	
	
	
	
	private List<String> errors=new ArrayList<String>();
	private String serverErrorMessage;
	
	
	
	
	public String getNameField() {		
		return nameField;
	}
	public void setNameField(String nameField) {
		this.nameField = nameField;
	}
	public String getMessageField() {
		return messageField;
	}
	public void setMessageField(String messageField) {
		this.messageField = messageField;
	}
	public String getServerErrorMessage() {
		return serverErrorMessage;
	}
	public void setServerErrorMessage(String serverErrorMessage) {
		this.serverErrorMessage = serverErrorMessage;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		if(!mailId.contains("@")) {
			errors.add("mailId Invalid");
			return;
		}
		this.mailId = mailId;
	}
	public String getRunningTextMessage() {
		return runningTextMessage;
	}
	public void setRunningTextMessage(String runningTextMessage) {
		this.runningTextMessage = runningTextMessage;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSmallMessage() {
		return smallMessage;
	}
	public void setSmallMessage(String smallMessage) {
		this.smallMessage = smallMessage;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	
	
	
	
	
	
	
}
