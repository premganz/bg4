package org.spo.svc3.trx.pgs.k02.toolkit;

import org.spo.ifs3.dsl.controller.ScopeVar;
import org.spo.ifs3.dsl.controller.TrxInfo;
import org.spo.ifs3.dsl.controller.TrxInfo.Scope;
import org.spo.svc3.trx.pgs.mdl.HomePage;

public class K02Toolkit {
	public static final ScopeVar SV_K02_HOM_PG= new ScopeVar(Scope.TRX, "SV_K02_HOM_PG");
	
	public static void setHomePage(TrxInfo info, HomePage homePageModel){
		info.put(SV_K02_HOM_PG, homePageModel);
	}

	public static HomePage getHomePage(TrxInfo info){
		return (HomePage) info.get(SV_K02_HOM_PG);
	}

}
