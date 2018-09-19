package org.spo.svc3.trx.pgs.mdl;

import java.util.List;

public class HomePage extends GuidePage {

	
	
	private Menu sideBarMenu;
	
	private String welcomeContentCode;
	private String welcomeContent;
	private String subTitle;
	
	private ActionPageSet actionPageSet;
	
	
	


	public ActionPageSet getActionPageSet() {
		return actionPageSet;
	}

	public void setActionPageSet(ActionPageSet actionPageSet) {
		this.actionPageSet = actionPageSet;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	

	public Menu getSideBarMenu() {
		return sideBarMenu;
	}

	public void setSideBarMenu(Menu sideBarMenu) {
		this.sideBarMenu = sideBarMenu;
	}

	public String getWelcomeContentCode() {
		return welcomeContentCode;
	}

	public void setWelcomeContentCode(String welcomeContentCode) {
		this.welcomeContentCode = welcomeContentCode;
	}

	public String getWelcomeContent() {
		return welcomeContent;
	}

	public void setWelcomeContent(String welcomeContent) {
		this.welcomeContent = welcomeContent;
	}
	
	
	
	
	
	
	
	
}
