package org.spo.svc3.trx.pgs.k01.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.cms3.svc.PageService;
import org.spo.cms3.svc.SocketConnector;
import org.spo.ifs3.dsl.controller.NavEvent;
import org.spo.ifs3.dsl.controller.TrxInfo;
import org.spo.ifs3.dsl.model.AbstractTask;
import org.spo.svc3.trx.pgs.mdl.HomePage;
import org.spo.svc3.trx.pgs.mdl.Menu;
import org.spo.svc3.trx.pgs.t01.toolkit.T01Toolkit;
import org.spo.svc3.trx.pgs.t02.handler.T02Handler;
import org.spo.svc3.trx.pgs.utils.MenuFactory;
import org.spo.svc3.trx.pgs.utils.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class K0101 extends AbstractTask {



	@Autowired
	public PageService svc ;
	private static final Logger logger = LoggerFactory.getLogger(K0101.class);

	
	private SocketConnector connector=new SocketConnector();
	
	@Override
	public NavEvent initTask(String dataId, TrxInfo info) throws Exception {

		HomePage page = new HomePage();

		page.setSubTitle("Welcome Page");

		String response_content = svc.readUpPage("camp_edu/camp_website", "A_Brief_Brouchure");
		page.setWelcomeContent(response_content);
		
		Menu sideBarMenu = new MenuFactory().homePageMenu();
		page.setSubTitle("hello");
		page.setSideBarMenu(sideBarMenu);
		info.addToModelMap("hom",page);

		System.out.println(page.toString());
		info.put(T01Toolkit.SV_T02_CONTENT_OVV, page);

		return T02Handler.EV_INIT_01;
	}

	@Override
	public NavEvent processViewEvent(String event, TrxInfo info) {
		if(event.startsWith("EV_DTL")){
			String dataId = event.replaceAll("EV_DTL_","");
			NavEvent navEvent = T02Handler.EV_SWITCH_TO_CONTENT;
			navEvent.dataId=dataId;
			return navEvent;
		}else if(event.startsWith("EV_SUB_LAND")){
			String dataId = event.replaceAll("EV_SUB_LAND_","");
			NavEvent navEvent = T02Handler.EV_SWITCH_SUB_LAND;
			navEvent.dataId=dataId;
			return navEvent;
		}else if(event.startsWith("EV_SHORTCUT")){
			String dataId = event.replaceAll("EV_SHORTCUT_","");
			NavEvent navEvent = T02Handler.EV_SWITCH_TO_CONTENT;
			navEvent.dataId=dataId;
			return navEvent;
		}
		else if(event.startsWith("EV_ABOUT")){			
			NavEvent navEvent = T02Handler.EV_SWITCH_TO_CONTENT;
			navEvent.dataId="B01T";
			return navEvent;
		}
		else if(event.startsWith("EV_BLOG")){		
			NavEvent navEvent = T02Handler.EV_SWITCH_TO_BLOG_LANDING;
			navEvent.dataId="LB01T";
			return navEvent;
		}
		return T02Handler.EV_INIT_01;
	}

	@Override
	public NavEvent processViewResult(String event,  String json, TrxInfo info) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String processAjaxEvent(String event, TrxInfo info) {
		return "";
	}

	public SocketConnector getConnector() {
		return connector;
	}

	public void setConnector(SocketConnector connector) {
		this.connector = connector;
	}

	@Override
	public NavEvent initView(TrxInfo info) {
		// TODO Auto-generated method stub
		return null;
	}




}
