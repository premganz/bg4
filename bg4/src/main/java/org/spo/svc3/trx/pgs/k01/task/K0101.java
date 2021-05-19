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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class K0101 extends AbstractTask {



	@Autowired
	public PageService svc ;
	private static final Logger logger = LoggerFactory.getLogger(K0101.class);

	
	private SocketConnector connector=new SocketConnector();
	
	@Override
	public NavEvent initTask(TrxInfo info) throws Exception {
		HomePage page = new HomePage();
		page.setSubTitle("Welcome Page");
		ActionAssembly aa = new ActionAssembly();
		aa.setCodes("About","about","","index");
		String response_content = svc.readUpPage(aa);
		page.setWelcomeContent(response_content);
		Menu sideBarMenu = new MenuFactory().homePageMenu();
//		page.setSubTitle("Forum for Scholarship and Exploration");
		page.setSubTitle("Reviving Cartesian Science");
		page.setSideBarMenu(sideBarMenu);
		info.addToModelMap("hom",page);
		System.out.println(page.toString());
		
		return K01Handler.EV_INIT_01;
	}

	@Override
	public NavEvent processViewEvent(String event, String dataId, TrxInfo info) {
		if(event.startsWith("EV_action")){
			dataId = dataId.replaceAll("action__","");	
			K01Toolkit.setActionCode(info, dataId);
			K01Toolkit.setMode(info, "action");
			return K01Handler.EV_MINOR_PAGE;
		}if(event.startsWith("EV_minor")) {
			try {
				dataId = dataId.replaceAll("minor__","");
				K01Toolkit.setMinorCode(info, dataId);
				K01Toolkit.setMode(info, "minor");
				return K01Handler.EV_MINOR_PAGE;
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			page.setSubTitle("hello");
//			page.setSideBarMenu(sideBarMenu);
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
