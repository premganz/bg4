package org.spo.ifs3.dsl.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;

public class TrxInfo {
	public  enum Scope {TRX, REQ, GBL};
	Map<ScopeVar,Object> info_req = new LinkedHashMap<ScopeVar,Object>() ;
	Map<ScopeVar,Object> info_trx = new LinkedHashMap<ScopeVar,Object>() ;
	HttpSession session;	
	
	
	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}


	public Object get(ScopeVar var){
		if(var.scope.equals(Scope.REQ)){
			return ((Map<ScopeVar,Object>)session.getAttribute("info_req")).get(var);
		}
		if(var.scope.equals(Scope.TRX)){
			return ((Map<ScopeVar,Object>)session.getAttribute("info_trx")).get(var);
		}
		return null;
	}
	
	
	public void put(ScopeVar var, Object object){
		if(var.scope.equals(Scope.REQ)){
			((Map<ScopeVar,Object>)session.getAttribute("info_req")).put(var,object);
		}
		if(var.scope.equals(Scope.TRX)){
			((Map<ScopeVar,Object>)session.getAttribute("info_trx")).put(var, object);
		}
		
	}

	
	public TrxInfo(HttpSession session, ModelMap map, HttpServletRequest request){
		this.session=session;
		info_req = new LinkedHashMap<ScopeVar,Object>() ;
		info_trx = new LinkedHashMap<ScopeVar,Object>() ;
//		for(String modelMapKey:map.keySet()){
//			info_req.put(new ScopeVar(Scope.REQ, modelMapKey), map.get(modelMapKey));	
//		}
		info_req.put(AbstractToolkit.SV_MODEL, map);
		session.setAttribute("info_trx", info_trx);
		session.setAttribute("info_req", info_req);
		
	}
	public void updateModelMap(ModelMap map){
		info_req.put(AbstractToolkit.SV_MODEL, map);
		
	}

	public void addToModelMap(String key, Object value){
		((ModelMap)info_req.get(AbstractToolkit.SV_MODEL)).addAttribute(key, value);
		
	}
	public void addToFormMap(String key, Object value){
		Forms forms = new Forms();
		LinkedHashMap<String,String> formMap = new LinkedHashMap<String,String>();
		formMap.put("usr_city", "MDU");
		forms.setForm(formMap);
		LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("forms",value);
		((ModelMap)info_req.get(AbstractToolkit.SV_MODEL)).addAttribute(key, value);
		
	}
	
		
	public StateInfo getState(){
		return ((StateInfo)info_trx.get(AbstractToolkit.SV_STATE));
	}
	public void refreshModelMap(ModelMap map){
		info_req.put(AbstractToolkit.SV_MODEL, map);
	}
}
	
	
