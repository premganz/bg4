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
import org.spo.svc3.trx.pgs.utils.CmsUtils;
import org.spo.svc3.trx.pgs.utils.MenuFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

@Component
public class K0101 extends AbstractTask {

//HOme page

	@Autowired
	public PageService svc ;
	private static final Logger logger = LoggerFactory.getLogger(K0101.class);

	
	private SocketConnector connector=new SocketConnector();
	
	@Override
	public NavEvent initTask(String dataId, TrxInfo info) throws Exception {
		HomePage page = new HomePage();
		page.setSubTitle("Welcome Page");
		ActionAssembly aa = new ActionAssembly();
		aa.setCodes("publish_expository_research","pub_info_meta","Energy_Problem");
		
		String response_content = svc.readUpPage(aa, true);
		page.setWelcomeContent(response_content);
		Menu sideBarMenu = new MenuFactory().homePageMenu();
		page.setSubTitle("hello");
		page.setSideBarMenu(sideBarMenu);
		info.addToModelMap("hom",page);
		System.out.println(page.toString());
		info.put(K01Toolkit.SV_K01_DOES_CONTENT, page);
		return T02Handler.EV_INIT_01;
	}

	@Override
	public NavEvent processViewEvent(String event, String dataId, TrxInfo info) {
		if(event.startsWith("EV_theme")){
			dataId = dataId.replaceAll("theme__","");
			NavEvent navEvent = K01Handler.EV_SWITCH_THEME_LAND;
			navEvent.dataId=dataId;
			return navEvent;
		}
		else if(event.startsWith("EV_does")){
			dataId = dataId.replaceAll("does__","");
			String xquery="//does[@nl='"+dataId.replaceAll("_", " ")+ "']/../..";
			CmsUtils utils = new CmsUtils();
			Element roleElement=null;
			try {
				roleElement = utils.util_queryHelper1(xquery);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			String roleCode = roleElement.getAttribute("lbl").replaceAll(" ", "_");
			info.put(K01Toolkit.SV_K01_ROLE_CODE, roleCode);
			NavEvent navEvent = K01Handler.EV_SWITCH_DOES_LAND;
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
