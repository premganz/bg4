package org.spo.svc4.trx.pgs.utils;

import org.spo.cms4.svc.PageService;
import org.spo.svc4.trx.pgs.mdl.ActionAssembly;
import org.spo.svc4.trx.pgs.mdl.HomePage;
import org.spo.svc4.trx.pgs.mdl.Menu;
import org.w3c.dom.NodeList;

public class PageFactory {


	public HomePage getHomePage() throws Exception{
		HomePage page = new HomePage();

		page.setSubTitle("Welcome Page");

		PageService svc = new PageService();
		ActionAssembly aa = new ActionAssembly().setCodes("About", "About",  null, "index");
		String response_content = svc.readUpPage(aa);
		page.setWelcomeContent(response_content);
		
		Menu sideBarMenu = new MenuFactory().homePageMenu();

		page.setSideBarMenu(sideBarMenu);
		return page;


	}
	
	
	public void getContentPage(String key) throws Exception{
		

		ActionAssembly embeddedPage = new ActionAssembly();
		NodeList nodeList = new MenuFactory().getByQuery("//");
		
	}
	
	



}
