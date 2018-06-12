package org.spo.svc3.trx.def;

import org.spo.ifs3.template.web.Constants;
import org.springframework.stereotype.Component;
@Component
public class ConstantsTestImpl implements Constants {

	public String getRepoPath() {
		if(!System.getProperty("os.name").startsWith("Windows")){
			return "/usr/local/share/data-cms/lc";
		}else{
			return "C:\\Users\\premganesh\\eclipse-workspace\\bg4\\src\\main\\resources\\data-cms\\";
		}
	}

	public String getLandingPage() {
		return "trx/T01/Wel_msg";
	}

	public int getPortNumber() {
		// TODO Auto-generated method stub
		return 8087;
	}

}
