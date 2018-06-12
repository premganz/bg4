package org.spo.ifs.template.web;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ConstantsOld {	
	
	public static String path_repo = "/usr/local/share/data-cms/lc";
	//public static  String path_repo = "C:\\Users\\premganesh\\git\\bg1_3\\src\\main\\resources\\data-cms";
	
	static{
		if(!System.getProperty("os.name").startsWith("Windows")){
			path_repo = "/usr/local/share/data-cms/lc";
		}else{
			path_repo = "C:\\Users\\premganesh\\git\\bg1_3\\src\\main\\resources\\data-cms";
		}
	}
	
//	static{
//		URL resourceUrl = Constants.class.getResource("/data-cms");
//		String resourcePath;
//		try {
//			resourcePath = resourceUrl.toURI().getPath();
//			 File file= new File(resourcePath);
//			 path_repo=file.getAbsolutePath();
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}
	
}
