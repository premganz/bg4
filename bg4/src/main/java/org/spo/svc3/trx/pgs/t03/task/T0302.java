package org.spo.svc3.trx.pgs.t03.task;

import java.lang.reflect.Type;

import org.spo.cms3.svc.PageService;
import org.spo.ifs3.dsl.controller.NavEvent;
import org.spo.ifs3.dsl.controller.TrxInfo;
import org.spo.ifs3.dsl.model.AbstractTask;
import org.spo.svc3.trx.pgs.t01.cmd.Home_pg;
import org.spo.svc3.trx.pgs.t01.cmd.Wel_msg;
import org.spo.svc3.trx.pgs.t01.handler.T01Handler;
import org.spo.svc3.trx.pgs.t03.cmd.Usr_Reg_I;
import org.spo.svc3.trx.pgs.t03.handler.T03Handler;
import org.spo.svc3.trx.pgs.t03.mappings.UsrMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Component
public class T0302 extends AbstractTask {
	@Autowired
	public PageService svc ;
	
	@Override
	public NavEvent initView(TrxInfo info) {
		System.out.println("in init view");
		return null;
	}

	@Override
	public NavEvent initTask(String dataId, TrxInfo info) throws Exception {
		UsrMappings map = new UsrMappings();
		
		info.addToModelMap("forms",map.populateFormReg(svc));
		
		return T03Handler.EV_INIT_01;
	
	}

	@Override
	public NavEvent processViewEvent(String event, TrxInfo info) {
		if(event.startsWith("EV_LOGIN")){
			String dataId = event.replaceAll("EV_LOGIN_","");
			NavEvent navEvent = T03Handler.EV_NAV_MENU;
			navEvent.dataId=dataId;
			return navEvent;
		}
		return T03Handler.EV_INIT_01;
	}

	@Override
	public NavEvent processViewResult(String event, String json, TrxInfo info) {
		System.out.println(json);
		UsrMappings map = new UsrMappings();
		Usr_Reg_I input = map.getFormData(json);
		if(!input.getUsr_city().equals("TCR")) {
			info.addToModelMap("forms",map.populateFormReg(svc));
			return T03Handler.EV_INIT_02;
		}
		
		return  T03Handler.EV_PVS_MENU;
	}
	
	@Override
	public String processAjaxEvent(String event, TrxInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	
		
	
}
