package org.spo.svc3.trx.pgs.k01.toolkit;

import org.spo.ifs3.dsl.controller.ScopeVar;
import org.spo.ifs3.dsl.controller.TrxInfo;
import org.spo.ifs3.dsl.controller.TrxInfo.Scope;

public class K01Toolkit {

	
	
	
public static final ScopeVar SV_K01_MINOR_CODE= new ScopeVar(Scope.TRX, "SV_K01_MINOR_CODE");
	


public static void setMinorCode(TrxInfo info, String dataId){
	info.put(SV_K01_MINOR_CODE, dataId);
}


public static String getMinorCode(TrxInfo info){
	return info.get(SV_K01_MINOR_CODE).toString();
}
	
}
