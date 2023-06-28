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
import org.spo.svc3.trx.pgs.utils.GsonUtils;
import org.spo.svc3.trx.pgs.utils.MenuFactory;
import org.spo.svc3.trx.pgs.utils.SchemaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

@Component
public class K0101 extends AbstractTask {



	@Autowired
	public PageService svc ;
	private static final Logger logger = LoggerFactory.getLogger(K0101.class);

	
	private SocketConnector connector=new SocketConnector();
	
	@Override
	public NavEvent initTask(TrxInfo info) throws Exception {
		logger.debug("in K0101");
		HomePage page = new HomePage();
		page.setSubTitle("Welcome Page");
		page.setPageTypeCode("CONTENT");
		ActionAssembly aa = new ActionAssembly();
		aa.setCodes("Campus","about","","");
		String response_content = svc.readUpPage(aa);
		page.setWelcomeContent(response_content);
		Menu sideBarMenu = new MenuFactory().homePageMenu();
//		page.setSubTitle("Forum for Scholarship and Exploration");
		page.setSubTitle("Minimalist Software Design");//agency for minimalist software design
		page.setSideBarMenu(sideBarMenu);
		Gson gson = GsonUtils.getGson();
		
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
		
		return K01Handler.EV_INIT_01;
	}

	@Override
	public NavEvent processViewEvent(String event, String dataId, TrxInfo info) {
		
		ActionAssembly aa = new ActionAssembly();
		SchemaQuery schemaQuery=null;
		try {
			schemaQuery = new SchemaQuery();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(event.startsWith("EV_minor")) {
			if(dataId.contains("Contact")) {
				return K01Handler.EV_CONTACT_PAGE;
			}
			
			dataId = dataId.replaceAll("minor__","");
			K01Toolkit.setMode(info, "minor");
			K01Toolkit.setMinorCode(info, dataId);
			aa=schemaQuery.getMinorLandingPage(K01Toolkit.getMinorCode(info));	
			K01Toolkit.setActionAssem(info, aa);
			
//			page.setStyleClass("blackbody_minor");
			return K01Handler.EV_MINOR_PAGE;
		}
		if(event.startsWith("EV_action")) {
			dataId = dataId.replaceAll("action__","");
//			page.setStyleClass("blackbody_action");
			K01Toolkit.setMode(info, "action");
			K01Toolkit.setActionCode(info, dataId);
			aa=schemaQuery.getActionLandingPage(K01Toolkit.getActionCode(info));	
			K01Toolkit.setActionAssem(info, aa);
			return K01Handler.EV_MINOR_PAGE;
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
