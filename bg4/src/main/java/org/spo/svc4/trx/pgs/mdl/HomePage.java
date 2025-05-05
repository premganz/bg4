package org.spo.svc4.trx.pgs.mdl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;

public class HomePage extends GuidePage {

	private Menu sideBarMenu;
	private String welcomeContent;
	private String subTitle;
	private String pageTypeCode;
	//CONTENT, LIST, OVERVIEW
	
	

	public String getPageTypeCode() {
		return pageTypeCode;
	}

	public void setPageTypeCode(String pageTypeCode) {
		this.pageTypeCode = pageTypeCode;
	}

	private ActionAssembly actionPageEmbedded;
	
	
	
	private List<ExtLink> permaLinks=new ArrayList<ExtLink>();;
	
	private ExtLink homePageLink;
	private ExtLink aboutPageLink;
	private String styleClass;
	
	private PageMeta pageMeta;
	
	
	
//TODO

	public PageMeta getPageMeta() {
		return pageMeta;
	}

	public void setPageMeta(PageMeta pageMeta) {
		this.pageMeta = pageMeta;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}


	

	

	public ActionAssembly getActionPageEmbedded() {
		return actionPageEmbedded;
	}

	public void setActionPageEmbedded(ActionAssembly actionPageEmbedded) {
		this.actionPageEmbedded = actionPageEmbedded;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	

	public List<ExtLink> getPermaLinks() {
		ExtLink ext = new ExtLink();
		ext.setHref("../");
		ext.setLabel("About");
		ext.setMetaInfo(" posted by prem");
		ext.setDescription("Back to Home");
		ext.setDate(DateTime.now());
		permaLinks.add(ext);
		 ext = new ExtLink();
		ext.setHref("../");
		ext.setLabel("Home");
		ext.setMetaInfo(" posted by prem");
		ext.setDescription("Back to Home");
		ext.setDate(DateTime.now());
		permaLinks.add(ext);
		
		return permaLinks;
	}

	
	
	
	
	public ExtLink getHomePageLink() {
		ExtLink homePageLink = new ExtLink();
		homePageLink.setHref("e://community.hortonworks.com/questions/146756/expression-language-for-concatenating.html");
		homePageLink.setLabel("Home");
		homePageLink.setMetaInfo(" posted by prem");
		homePageLink.setDescription("dfads");
		homePageLink.setDate(DateTime.now());
		return homePageLink;
	}

	public void setHomePageLink(ExtLink homePageLink) {
		this.homePageLink = homePageLink;
	}

	public ExtLink getAboutPageLink() {
		ExtLink abtPageLink = new ExtLink();
		abtPageLink.setHref("e://community.hortonworks.com/questions/146756/expression-language-for-concatenating.html");
		abtPageLink.setLabel("Home");
		abtPageLink.setMetaInfo(" posted by prem");
		abtPageLink.setDescription("dfads");
		abtPageLink.setDate(DateTime.now());
		return abtPageLink;
	}

	public void setAboutPageLink(ExtLink aboutPageLink) {
		this.aboutPageLink = aboutPageLink;
	}

	public void setPermaLinks(List<ExtLink> permaLinks) {
		this.permaLinks = permaLinks;
	}

	public Menu getSideBarMenu() {
		return sideBarMenu;
	}

	public void setSideBarMenu(Menu sideBarMenu) {
		this.sideBarMenu = sideBarMenu;
	}

	

	public String getWelcomeContent() {
		return welcomeContent;
	}

	public void setWelcomeContent(String welcomeContent) {
		this.welcomeContent = welcomeContent;
	}
	
	
	
	
	
	
	
	
}
