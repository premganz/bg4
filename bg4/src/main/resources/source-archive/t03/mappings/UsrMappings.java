package org.spo.svc3.trx.pgs.t03.mappings;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import org.spo.cms3.svc.PageService;
import org.spo.ifs3.dsl.controller.Forms;
import org.spo.svc3.trx.pgs.t03.cmd.Usr_Menu;
import org.spo.svc3.trx.pgs.t03.cmd.Usr_Reg_I;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class UsrMappings {
	Forms forms = new Forms();
	LinkedHashMap<String,String> formMap = new LinkedHashMap<String,String>();
	Usr_Reg_I in = new Usr_Reg_I();
	Gson gson = new Gson();

	public Forms populateFormReg(PageService svc) {
		String json =  svc.readUpPage("posts", "Usr_Reg_I");
		Type typ = new TypeToken<LinkedHashMap<String,String>>(){}.getType();//FIXME right now only string works
		LinkedHashMap<String,String> formMap= gson.fromJson(json,typ);

		
		formMap.put("usr_id", "DEFAULTID");
		formMap.put("usr_nm","");



		forms.setForm(formMap);
		return forms;


	}

	public Usr_Menu populateFormMenu(PageService svc) {
		String json =  svc.readUpPage("posts", "Usr_Menu");
		Type typ = new TypeToken<Usr_Menu>(){}.getType();//FIXME right now only string works
		Usr_Menu reg= gson.fromJson(json,typ);	
		return reg;


	}


	public Usr_Reg_I getFormData(String json) {
		Type typ = new TypeToken<Usr_Reg_I>(){}.getType();//FIXME right now only string works
		Usr_Reg_I reg= gson.fromJson(json,typ);	
		return reg;
	}


}
