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
import org.spo.svc3.trx.pgs.utils.MenuFactory;
import org.spo.svc3.trx.pgs.utils.SchemaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class K0102 extends AbstractTask {

	@Autowired
	public PageService svc ;
	private static final Logger logger = LoggerFactory.getLogger(K0102.class);

	
	private SocketConnector connector=new SocketConnector();
	
	@Override
	public NavEvent initTask(TrxInfo info) throws Exception {
		HomePage page = new HomePage();
		page.setSubTitle("Welcome Page");
		ActionAssembly aa = new ActionAssembly();
		SchemaQuery schemaQuery = new SchemaQuery();
		aa=schemaQuery.getMinorLandingPage(K01Toolkit.getMinorCode(info));
		String response_content = svc.readUpPage(aa);
		page.setWelcomeContent(response_content);
		Menu sideBarMenu = new MenuFactory().subPageMenu(K01Toolkit.getMinorCode(info));
		page.setSubTitle(K01Toolkit.getMinorCode(info).replaceAll("_", " ").toUpperCase()+"");
		page.setSideBarMenu(sideBarMenu);
		info.addToModelMap("hom",page);
		System.out.println(page.toString());	
		return K01Handler.EV_INIT_02;
	}

	@Override
	public NavEvent processViewEvent(String event, String dataId, TrxInfo info) {
		if(event.startsWith("EV_action")){
			dataId = dataId.replaceAll("action__","");
			NavEvent navEvent = K01Handler.EV_INIT_02;
			navEvent.dataId=dataId;
			return navEvent;
		}if(event.startsWith("EV_minor")) {}
		return K01Handler.EV_INIT_02;
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
