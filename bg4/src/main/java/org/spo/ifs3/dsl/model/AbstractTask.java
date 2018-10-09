package org.spo.ifs3.dsl.model;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import org.spo.ifs3.dsl.controller.Forms;
import org.spo.ifs3.dsl.controller.NavEvent;
import org.spo.ifs3.dsl.controller.TrxInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class AbstractTask {

	public abstract NavEvent  initView(TrxInfo info);
	public abstract NavEvent initTask(String dataId, TrxInfo info) throws Exception;
	//public abstract NavEvent initView(String dataId, ModelMap info);
	public abstract NavEvent processViewEvent(String event, String dataId,TrxInfo info);	
	public abstract String processAjaxEvent(String event, TrxInfo info);
	public abstract NavEvent processViewResult(String event, String json, TrxInfo info);
	
	public Forms prepareForm(String json){
		Gson gson = new Gson();
		Map<String,String> map = new LinkedHashMap<String,String>(); 
		Type typ1 = new TypeToken<Map<String, String>>(){}.getType();
		//map = gson.fromJson("{\"hello\":\"hi\"}",typ1);
		map = gson.fromJson(json,typ1);
		Forms forms = new Forms();
		forms.setForm(map);
		return forms;
				
	}
	
	

	
}

