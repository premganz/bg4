package org.spo.cms4.svc;

public class CMSValidationException extends RuntimeException{
@Override
public String getMessage() {
	
	return "SOMETHING WRONG IN THE DATA ASKED TO BE PROCESSED BY CMS"+super.getMessage();
}
}
