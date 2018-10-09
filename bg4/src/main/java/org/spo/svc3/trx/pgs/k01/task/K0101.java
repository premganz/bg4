package org.spo.svc3.trx.pgs.k01.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.cms3.svc.PageService;
import org.spo.cms3.svc.SocketConnector;
import org.spo.ifs3.dsl.controller.NavEvent;
import org.spo.ifs3.dsl.controller.TrxInfo;
import org.spo.ifs3.dsl.model.AbstractTask;
import org.spo.svc3.trx.pgs.k01.handler.K01Handler;
import org.spo.svc3.trx.pgs.k01.toolkit.K01Toolkit;
import org.spo.svc3.trx.pgs.mdl.ActionAssembly;
import org.spo.svc3.trx.pgs.mdl.HomePage;
import org.spo.svc3.trx.pgs.mdl.Menu;
import org.spo.svc3.trx.pgs.t02.handler.T02Handler;
import org.spo.svc3.trx.pgs.utils.MenuFactory;
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
		ActionAssembly aa = new ActionAssembly();
		aa.setCodes("about","abt_why","Why_Projects_like_this_should_exist");
		String response_content = svc.readUpPage(aa);
		page.setWelcomeContent(response_content);
		Menu sideBarMenu = new MenuFactory().homePageMenu();
		page.setSubTitle("hello");
		page.setSideBarMenu(sideBarMenu);
		info.addToModelMap("hom",page);
		System.out.println(page.toString());
		info.put(K01Toolkit.SV_K01_CONTENT_OVV, page);
		return T02Handler.EV_INIT_01;
	}

	@Override
	public NavEvent processViewEvent(String event, String dataId, TrxInfo info) {
		if(event.startsWith("EV_theme")){
			dataId = dataId.replaceAll("theme__","");
			NavEvent navEvent = K01Handler.EV_SWITCH_SUB_LAND;
			navEvent.dataId=dataId;
			return navEvent;
		}
		return K01Handler.EV_INIT_01;
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
