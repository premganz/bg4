package org.spo.svc3.trx.pgs.mdl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomePage extends GuidePage {

	private Menu sideBarMenu;
	private String welcomeContent;
	private String subTitle;
	
	private ActionAssembly actionPageEmbedded;
	private List<ExtLink> newsItems=new ArrayList<ExtLink>();
	
	private boolean exploreMoreLinkIc_1;
	private String exploreMoreLinkTxt_1;
	
	private List<ExtLink> permaLinks=new ArrayList<ExtLink>();;
	
	private ExtLink homePageLink;
	private ExtLink aboutPageLink;
	
	
//TODO

	public boolean isExploreMoreLinkIc_1() {
		return exploreMoreLinkIc_1;
	}

	public void setExploreMoreLinkIc_1(boolean exploreMoreLinkIc_1) {
		this.exploreMoreLinkIc_1 = exploreMoreLinkIc_1;
	}

	public List<ExtLink> getNewsItems() {
		ExtLink ext = new ExtLink();
		ext.setHref("e://community.hortonworks.com/questions/146756/expression-language-for-concatenating.html");
		ext.setLabel("The Mullberry Tree August 10, 2018");
		ext.setMetaInfo(" Sourced from The website , www.leafycampus.org");
		ext.setDescription("dfads");
		ext.setDate(Calendar.getInstance().getTime());
		newsItems.add(ext);
		return newsItems;
	}

	public void setNewsItems(List<ExtLink> newsItems) {
		this.newsItems = newsItems;
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
		ext.setHref("e://community.hortonworks.com/questions/146756/expression-language-for-concatenating.html");
		ext.setLabel("About");
		ext.setMetaInfo(" posted by prem");
		ext.setDescription("dfads");
		ext.setDate(Calendar.getInstance().getTime());
		permaLinks.add(ext);
		 ext = new ExtLink();
		ext.setHref("e://community.hortonworks.com/questions/146756/expression-language-for-concatenating.html");
		ext.setLabel("Home");
		ext.setMetaInfo(" posted by prem");
		ext.setDescription("dfads");
		ext.setDate(Calendar.getInstance().getTime());
		permaLinks.add(ext);
		
		return permaLinks;
	}

	
	
	
	
	public ExtLink getHomePageLink() {
		ExtLink homePageLink = new ExtLink();
		homePageLink.setHref("e://community.hortonworks.com/questions/146756/expression-language-for-concatenating.html");
		homePageLink.setLabel("Home");
		homePageLink.setMetaInfo(" posted by prem");
		homePageLink.setDescription("dfads");
		homePageLink.setDate(Calendar.getInstance().getTime());
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
		abtPageLink.setDate(Calendar.getInstance().getTime());
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

	public String getExploreMoreLinkTxt_1() {
		return exploreMoreLinkTxt_1;
	}

	public void setExploreMoreLinkTxt_1(String exploreMoreLinkTxt_1) {
		this.exploreMoreLinkTxt_1 = exploreMoreLinkTxt_1;
	}
	
	
	
	
	
	
	
	
}
