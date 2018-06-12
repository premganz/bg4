package org.spo.ifs3.dsl.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.spo.ifs3.dsl.controller.TrxInfo.Scope;

public class AbstractToolkit {

	public static final ScopeVar SV_MODEL=new ScopeVar(Scope.REQ,"modelMap");
	public static final ScopeVar SV_STATE=new ScopeVar(Scope.TRX,"stateInfo");
	public static final ScopeVar SV_FORM = new ScopeVar(Scope.REQ, "form");
	
}
