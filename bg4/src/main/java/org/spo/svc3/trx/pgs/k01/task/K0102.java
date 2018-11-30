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
import org.spo.svc3.trx.pgs.utils.CmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

@Component
public class K0102 extends AbstractTask {



	@Autowired
	public PageService svc ;
	private static final Logger logger = LoggerFactory.getLogger(K0102.class);

	
	private SocketConnector connector=new SocketConnector();
	
	@Override
	public NavEvent initTask(String dataId, TrxInfo info) throws Exception {
		ActionAssembly aa = new ActionAssembly();
		
		HomePage page =(HomePage)info.get(K01Toolkit.SV_K01_CONTENT_OVV);
		page.setSubTitle("THEME LANDING");
		CmsUtils utils = new CmsUtils();
		String xquery="//theme[@nl='"+dataId.replaceAll("_", " ")+ "']/../../..";
//		String xquery= "//does/intent/strategy/theme[@nl='"+ dataId.replaceAll("_", " ")+ "']" ;
		Element doesElement = utils.util_queryHelper1(xquery);		
		String doesCode = doesElement.getAttribute("nl").replaceAll(" ", "_");
		
		xquery= "//theme[@nl='"+ dataId.replaceAll("_", " " )+ "']/visit[1]/domain[1]" ;
		Element domainElement = utils.util_queryHelper1(xquery);
		String domainCode = domainElement.getAttribute("nl");
		
		xquery= "//domain[@nl='"+ domainCode+ "']/action[1]" ;
		Element actionElement = utils.util_queryHelper1(xquery);
		String actionCode = actionElement .getAttribute("nl");
		
		aa.setDoesCode(doesCode);
		aa.setDomainCode(domainCode.replaceAll(" ", "_"));
		aa.setActionCode(actionCode.replaceAll(" ", "_"));
		
		info.addToModelMap("theme",page);
		return K01Handler.EV_INIT_02;
	}

	@Override
	public NavEvent processViewEvent(String event, String dataId,TrxInfo info) {
		info.addToModelMap("hom",info.get(K01Toolkit.SV_K01_CONTENT_OVV));
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
