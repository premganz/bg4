package org.spo.svc3.trx.pgs.k01.toolkit;

import org.spo.ifs3.dsl.controller.ScopeVar;
import org.spo.ifs3.dsl.controller.TrxInfo;
import org.spo.ifs3.dsl.controller.TrxInfo.Scope;

public class K01Toolkit {

	public static final ScopeVar SV_K01_MINOR_CODE= new ScopeVar(Scope.TRX, "SV_K01_MINOR_CODE");
	public static final ScopeVar SV_K01_ACTION_CODE= new ScopeVar(Scope.TRX, "SV_K01_ACTION_CODE");
	public static final ScopeVar SV_K01_ARTICLE_CODE= new ScopeVar(Scope.TRX, "SV_K01_ARTICLE_CODE");
	public static final ScopeVar SV_K01_MODE= new ScopeVar(Scope.TRX, "SV_K01_MODE");

	public static void setMode(TrxInfo info, String modeCode){
		info.put(SV_K01_MODE, modeCode);
	}


	public static String getMode(TrxInfo info){
		return info.get(SV_K01_MODE).toString();
	}

	public static void setMinorCode(TrxInfo info, String dataId){
		info.put(SV_K01_MINOR_CODE, dataId);
	}


	public static String getMinorCode(TrxInfo info){
		return info.get(SV_K01_MINOR_CODE).toString();
	}


	public static void setActionCode(TrxInfo info, String dataId){
		info.put(SV_K01_ACTION_CODE, dataId);
	}


	public static String getActionCode(TrxInfo info){
		return info.get(SV_K01_ACTION_CODE).toString();
	}
	
	public static void setArticleCode(TrxInfo info, String dataId){
		info.put(SV_K01_ARTICLE_CODE, dataId);
	}


	public static String getArticleCode(TrxInfo info){
		return info.get(SV_K01_ARTICLE_CODE).toString();
	}


}
