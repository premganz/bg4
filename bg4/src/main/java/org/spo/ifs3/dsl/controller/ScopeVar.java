package org.spo.ifs3.dsl.controller;

import org.spo.ifs3.dsl.controller.TrxInfo.Scope;

public class ScopeVar{

	public Scope scope;
	public String key;

	public ScopeVar(Scope scope, String key){
		this.scope=scope;
		this.key=key;
	}


	@Override
	public String toString() {

		return scope+":"+key;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ScopeVar){
			ScopeVar varIn = (ScopeVar)obj;
			return varIn.toString().equals(this.toString());
		}
		return false;

	}
	
	
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result
	                + ((toString() == null) ? 0 : toString().hashCode());
	        return result;
	    }


}