package org.spo.svc4.trx.pgs.itf;

import java.util.ArrayList;
import java.util.List;

public class HomeItf {

	private List<RecordMenu> menus = new ArrayList<RecordMenu>();
	private String homePageContentId;
	
	
	public List<RecordMenu> getMenus() {
		return menus;
	}
	public void setMenus(List<RecordMenu> menus) {
		this.menus = menus;
	}
	public String getHomePageContentId() {
		return homePageContentId;
	}
	public void setHomePageContentId(String homePageContentId) {
		this.homePageContentId = homePageContentId;
	}
	
	
}


class RecordMenu{
	private String displayLabel;
	private String contentLinkId; //derived from natural language description
	
	
	
	private boolean isThemeLink;
	private boolean isVisitLink;
	private boolean isDoesLink;
	public String getDisplayLabel() {
		return displayLabel;
	}
	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}
	public String getContentLinkId() {
		return contentLinkId;
	}
	public void setContentLinkId(String contentLinkId) {
		this.contentLinkId = contentLinkId;
	}
	public boolean isThemeLink() {
		return isThemeLink;
	}
	public void setThemeLink(boolean isThemeLink) {
		this.isThemeLink = isThemeLink;
	}
	public boolean isVisitLink() {
		return isVisitLink;
	}
	public void setVisitLink(boolean isVisitLink) {
		this.isVisitLink = isVisitLink;
	}
	public boolean isDoesLink() {
		return isDoesLink;
	}
	public void setDoesLink(boolean isDoesLink) {
		this.isDoesLink = isDoesLink;
	}
	
	
		
}