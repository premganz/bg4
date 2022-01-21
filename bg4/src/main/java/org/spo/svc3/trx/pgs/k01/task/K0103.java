package org.spo.svc3.trx.pgs.k01.task;

import java.lang.reflect.Type;

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
import org.spo.svc3.trx.pgs.mdl.PageMeta;
import org.spo.svc3.trx.pgs.utils.MenuFactory;
import org.spo.svc3.trx.pgs.utils.SchemaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

@Component
public class K0103 extends AbstractTask {

	@Autowired
	public PageService svc ;
	private static final Logger logger = LoggerFactory.getLogger(K0103.class);


	private SocketConnector connector=new SocketConnector();

	@Override
	public NavEvent initTask(TrxInfo info) throws Exception {
		HomePage page = new HomePage();
		page.setSubTitle("Welcome Page");
		ActionAssembly aa = K01Toolkit.getActionAssem(info);
		page.setStyleClass("blackbody_article");
		String response_content = svc.readUpPage(aa);
		page.setWelcomeContent(response_content);
		page.setPageTypeCode("CONTENT");
		Menu sideBarMenu = new MenuFactory().subPageMenu(aa.getMinorCode());
		page.setSubTitle(aa.getMinorCode().replaceAll("_", " ").toUpperCase()+"");
		page.setSideBarMenu(sideBarMenu);
		Gson gson = new Gson();
		Type typ = new TypeToken<PageMeta>(){}.getType();//FIXME right now only string works
		PageMeta pagemeta = null;
		try {
			pagemeta= gson.fromJson(svc.readUpPageMeta(aa),typ);	
		}catch (JsonParseException e) {
			pagemeta = new PageMeta();	
		}
		page.setPageMeta(pagemeta);
		info.addToModelMap("hom",page);
		System.out.println(page.toString());	
		return K01Handler.EV_INIT_03;
	}

	@Override
	public NavEvent processViewEvent(String event, String dataId, TrxInfo info) {
		HomePage page = new HomePage();
		ActionAssembly aa = new ActionAssembly();
		SchemaQuery schemaQuery=null;
		try {
			schemaQuery = new SchemaQuery();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(event.startsWith("EV_minor")) {
			dataId = dataId.replaceAll("minor__","");
			K01Toolkit.setMode(info, "minor");
			K01Toolkit.setMinorCode(info, dataId);
			aa=schemaQuery.getMinorLandingPage(K01Toolkit.getMinorCode(info));	
			K01Toolkit.setActionAssem(info, aa);
			return K01Handler.EV_MINOR_PAGE;
		}
		if(event.startsWith("EV_action")) {
			dataId = dataId.replaceAll("action__","");
			K01Toolkit.setMode(info, "action");
			K01Toolkit.setActionCode(info, dataId);
			aa=schemaQuery.getActionLandingPage(K01Toolkit.getActionCode(info));	
			K01Toolkit.setActionAssem(info, aa);
			return K01Handler.EV_MINOR_PAGE;
		}
		if(event.startsWith("EV_article")) {
			dataId = dataId.replaceAll("article__","");
			dataId = dataId.replaceAll("%20"," ");
			K01Toolkit.setMode(info, "article");
			K01Toolkit.setArticleCode(info, dataId);
			aa=schemaQuery.getArticlePage(K01Toolkit.getArticleCode(info));
			K01Toolkit.setActionAssem(info, aa);
			return K01Handler.EV_ARTICLE_PAGE;
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