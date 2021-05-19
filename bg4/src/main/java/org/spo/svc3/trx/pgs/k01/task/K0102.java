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
		if(K01Toolkit.getMode(info).equals("minor")) {
			aa=schemaQuery.getMinorLandingPage(K01Toolkit.getMinorCode(info));	
		}
		if(K01Toolkit.getMode(info).equals("action")) {
			aa=schemaQuery.getActionLandingPage(K01Toolkit.getActionCode(info));	
		}
		String response_content = svc.readUpPage(aa);
		page.setWelcomeContent(response_content);
		page.setStyleClass("blackbody_minor");
		Menu sideBarMenu = new MenuFactory().subPageMenu(K01Toolkit.getMinorCode(info));
		page.setSubTitle(K01Toolkit.getMinorCode(info).replaceAll("_", " ").toUpperCase()+"");
		page.setSideBarMenu(sideBarMenu);
		info.addToModelMap("hom",page);
		System.out.println(page.toString());	
		return K01Handler.EV_INIT_02;
	}

	@Override
	public NavEvent processViewEvent(String event, String dataId, TrxInfo info) {
		HomePage page = new HomePage();
		ActionAssembly aa = new ActionAssembly();
		if(event.startsWith("EV_minor")) {
			dataId = dataId.replaceAll("minor__","");
			K01Toolkit.setMode(info, "minor");
			K01Toolkit.setMinorCode(info, dataId);
		}
		if(event.startsWith("EV_action")) {
			dataId = dataId.replaceAll("action__","");
			page.setStyleClass("blackbody_action");
			K01Toolkit.setMode(info, "action");
			K01Toolkit.setActionCode(info, dataId);
		}
		if(event.startsWith("EV_article")) {
			dataId = dataId.replaceAll("article__","");
			dataId = dataId.replaceAll("%20"," ");
			K01Toolkit.setMode(info, "article");
			page.setStyleClass("blackbody_article");
			K01Toolkit.setArticleCode(info, dataId);
		}
		SchemaQuery schemaQuery;
		try {
			schemaQuery = new SchemaQuery();

			if(K01Toolkit.getMode(info).equals("minor")) {
				aa=schemaQuery.getMinorLandingPage(K01Toolkit.getMinorCode(info));	
			}
			if(K01Toolkit.getMode(info).equals("action")) {
				aa=schemaQuery.getActionLandingPage(K01Toolkit.getActionCode(info));	
			}
			if(K01Toolkit.getMode(info).equals("article")) {
				aa=schemaQuery.getArticlePage(K01Toolkit.getArticleCode(info));	
			}
			String response_content = svc.readUpPage(aa);
			page.setWelcomeContent(response_content);
			Menu sideBarMenu = new MenuFactory().subPageMenu(K01Toolkit.getMinorCode(info));
			page.setSubTitle(K01Toolkit.getMinorCode(info).replaceAll("_", " ").toUpperCase()+"");
			page.setSideBarMenu(sideBarMenu);
			info.addToModelMap("hom",page);
			System.out.println(page.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
