package org.spo.cms3.controller;

import java.util.Map;

public class PostContent {

	private String htmlContent;
	private String meta;
	private String path1;
	private String path2;
	private String path3;
	private String path4;
	public String getPath4() {
		return path4;
	}

	public void setPath4(String path4) {
		this.path4 = path4;
	}

	private boolean publishReady;
	
	/**MAnually Added**/
	private Map<String,String> metaMap;

	public boolean getPublishReady() {
		return publishReady;
	}

	public void setPublishReady(boolean publishReady) {
		this.publishReady = publishReady;
	}

	private String id;
	private String formErrors;




	public String getFormErrors() {
		return formErrors;
	}

	public void setFormErrors(String formErrors) {
		this.formErrors = formErrors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public Map<String, String> getMetaMap() {
		return metaMap;
	}

	public void setMetaMap(Map<String, String> metaMap) {
		this.metaMap = metaMap;
	}

	public String getMeta() {		
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
//		metaMap=new LinkedHashMap<String,String>();
//		try{
//			String[] metaArr = meta.split(";");
//			for(String expr:metaArr){	
//				metaMap.put(expr.split("=")[0],expr.split("=")[1]);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			throw new CMSValidationException();
//		}
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public String getPath1() {
		return path1;
	}

	public void setPath1(String path1) {
		this.path1 = path1;
	}

	public String getPath2() {
		return path2;
	}

	public void setPath2(String path2) {
		this.path2 = path2;
	}

	public String getPath3() {
		return path3;
	}

	public void setPath3(String path3) {
		this.path3 = path3;
	}

	



}
