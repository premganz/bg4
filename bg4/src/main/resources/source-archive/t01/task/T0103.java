package org.spo.svc3.trx.pgs.t01.task;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.cms3.svc.PageService;
import org.spo.cms3.svc.SocketConnector;
import org.spo.ifs3.dsl.controller.NavEvent;
import org.spo.ifs3.dsl.controller.TrxInfo;
import org.spo.ifs3.dsl.model.AbstractTask;
import org.spo.svc3.trx.pgs.t01.cmd.Home_pg;
import org.spo.svc3.trx.pgs.t01.cmd.Wel_msg;
import org.spo.svc3.trx.pgs.t01.handler.T01Handler;
import org.spo.svc3.trx.pgs.t01.toolkit.T01Toolkit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class T0103 extends AbstractTask {



	private static final Logger logger = LoggerFactory.getLogger(T0103.class);
	
	@Autowired
	public PageService svc ;
	
	private SocketConnector connector=new SocketConnector();
	
	@Override
	public NavEvent initTask(String dataId, TrxInfo info) {


		String dataId_Content= dataId;//Here dataId represents the template incoming because it is mostly a set of links in this page wiht a small intro
		String response="";
		String response_content="";

		response = svc.readUpPage("templates", "Home_pg");//Menu
		Gson gson = new Gson();
		
		Type typ = new TypeToken<Home_pg>(){}.getType();//FIXME right now only string works
		
		try{
			response_content = svc.readUpPage("posts", "Wel_msg_inst");
			typ = new TypeToken<Wel_msg>(){}.getType();//FIXME right now only string works
			Wel_msg cmd= gson.fromJson(response_content,typ);		
			info.addToModelMap("message",cmd);
			System.out.println(cmd.toString());
			info.put(T01Toolkit.SV_T02_CONTENT_OVV, cmd);
		}catch(Exception e){
			System.out.println("Error during messagePayload processing from  TestResourceServerException on" );
			e.printStackTrace();
		}

		return T01Handler.EV_INIT_03;
	
	}

	@Override
	public NavEvent processViewEvent(String event, String data, TrxInfo info) {
		if(event.startsWith("EV_DTL")){
			String dataId = event.replaceAll("EV_DTL_","");
			NavEvent navEvent = T01Handler.EV_REFRESH_CONTENT;
			navEvent.dataId=dataId;
			return navEvent;
		}if(event.startsWith("EV_PVS")){
			String dataId = "Wel_msg";
			NavEvent navEvent = T01Handler.EV_PVS_01;
			navEvent.dataId=dataId;
			return navEvent;
		}else if(event.startsWith("EV_SUB_LAND")){
			String dataId = event.replaceAll("EV_SUB_LAND_","");
			NavEvent navEvent = T01Handler.EV_REFRESH_NEW_SUB_LAND;
			navEvent.dataId=dataId;
			return navEvent;
		}
		return T01Handler.EV_SWITCH_TO_OVV;
	}

	
	@Override
	public NavEvent processViewResult(String event, String json, TrxInfo info) {
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
