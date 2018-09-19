package org.spo.svc3.trx.def;

import org.spo.ifs3.template.web.Constants;
import org.spo.svc3.config.AppConstants;
import org.springframework.stereotype.Component;
@Component
public class ConstantsTestImpl implements Constants {

	public String getRepoPath() {
		if(!System.getProperty("os.name").startsWith("Windows")){
			AppConstants.cmsDir= "/usr/local/share/data-cms/lc";
		}else{
			AppConstants.cmsDir= "C:\\Users\\premganesh\\git\\bg4\\bg4\\src\\main\\resources\\data-cms\\cms1";
		}
		return AppConstants.cmsDir;
	}

	public String getLandingPage() {
		return "trx/K01/Msg_01";
	}

	public int getPortNumber() {
		// TODO Auto-generated method stub
		return 8087;
	}

}
