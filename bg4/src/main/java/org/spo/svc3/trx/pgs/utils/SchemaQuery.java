package org.spo.svc3.trx.pgs.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.spo.ifs3.template.web.Constants;
import org.spo.svc3.trx.def.ConstantsTestImpl;
import org.spo.svc3.trx.pgs.mdl.ActionAssembly;
import org.spo.svc3.trx.pgs.mdl.Menu;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SchemaQuery extends MenuFactory{
//	@Autowired
//	Constants constants;
	static Document doc;
	Constants constants = new ConstantsTestImpl();
	
	
	public  SchemaQuery() throws Exception{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		//docFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//		doc = docBuilder.parse(constants.getRepoPath()+"/templates/Schema.xml");
		doc = docBuilder.parse(constants.getRepoPath()+"/content/meta/meta-schema/Schema_cms.xml");
	}
	
		
	

	public  ActionAssembly getMinorLandingPage(String minorId) throws Exception{
		ActionAssembly aa = new ActionAssembly();
		
		String majorCode = getSubMenuList("//minor[@id=\""+minorId+"\"]/..").get(0).getId();
		aa.setCodes(majorCode, minorId, "index");
		return aa;
	}
	
	
	
	
	
}
