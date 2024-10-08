package org.spo.svc3.trx.def;

import org.spo.ifs3.template.web.Constants;
import org.spo.svc3.config.AppConstants;
import org.springframework.stereotype.Component;
@Component
public class ConstantsTestImpl implements Constants {

	public String getRepoPath() {
		if(!System.getProperty("os.name").startsWith("Windows")){
			AppConstants.cmsDir= System.getenv("cmsdir");
			//"/home/ubuntu/data-cms/cms1"
		}else{
			String homePath = System.getenv("HOMEPATH");
			AppConstants.cmsDir= "c:"+homePath
					+ "/git/bg4-2/bg4/src/main/resources/data-cms/cms1";
		}
		return AppConstants.cmsDir;
	}

	public String getLandingPage() {
		if(!System.getProperty("os.name").startsWith("Windows")){
//			return "trx/K03/NullId";	
			return "content/pages/home";
		}
		return "content/pages/home";
		
		
	}

	public int getPortNumber() {
		if(!System.getProperty("os.name").startsWith("Windows")){
			return 80;
		}	
		return 8089;
	}

}
