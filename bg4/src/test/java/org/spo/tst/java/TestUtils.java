package org.spo.tst.java;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.spo.svc3.config.AppConstants;
import org.spo.svc3.trx.pgs.mdl.Menu;
import org.spo.svc3.trx.pgs.utils.CmsUtils;
import org.spo.svc3.trx.pgs.utils.MenuFactory;

public  class TestUtils {

	
	@Before
	public void initConstants() {
		if(System.getProperty("os.name").equals("Windows")){
		AppConstants.cmsDir="C:/Users/premganesh/git/bg4/bg4/src/main/resources/data-cms";
		}else{
			AppConstants.cmsDir="/usr/local/share/data-cms";
		}
	}
	
	
//	@Test
	public void testMenuUtils()  throws Exception{
		
		MenuFactory menuUtil = new MenuFactory();
		Menu menu = menuUtil.homePageMenu();
		System.out.println("test done");
		
		
		
	}
	
	@Test //RUN VERY CAREFULLY
	public void testCmsUtils()  throws Exception{
		
		CmsUtils cmsUtil = new CmsUtils();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		//docFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//		cmsUtil.doc=docBuilder.parse("C:/Users/premganesh/git/bg4/bg4/src/test/resources/ApplicationNavTreeModelGeneric.xml");
		cmsUtil.organizeFolders();
		cmsUtil.organizeFoldersMeta();
		
		
		
		
	}
	
}
