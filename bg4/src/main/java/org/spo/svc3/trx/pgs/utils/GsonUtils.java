package org.spo.svc3.trx.pgs.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
	
	
	public static Gson getGson() {
		
		Gson gson = new GsonBuilder().setDateFormat("yyyymmdd").create();
		return gson;
	}
	
}
