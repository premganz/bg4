package org.spo.cms3.controller;

import java.util.Map;

public class PostContent {

	private String htmlContent;
	private String meta;
	/**MAnually Added**/
	private Map<String,String> metaMap;
	private String classifLvl1;
	private String classifLvl2;
	private String mdfId;
	
	private String id;
	private String formErrors;


	


	public String getMdfId() {
		return mdfId;
	}

	public void setMdfId(String mdfId) {
		this.mdfId = mdfId;
	}

	public String getClassifLvl1() {
		return classifLvl1;
	}

	public void setClassifLvl1(String classifLvl1) {
		this.classifLvl1 = classifLvl1;
	}

	public String getClassifLvl2() {
		return classifLvl2;
	}

	public void setClassifLvl2(String classifLvl2) {
		this.classifLvl2 = classifLvl2;
	}

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



}
