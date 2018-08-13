package org.spo.svc3.trx.pgs.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.spo.ifs3.template.web.Constants;
import org.spo.svc3.trx.def.ConstantsTestImpl;
import org.spo.svc3.trx.pgs.mdl.Menu;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MenuUtil {
//	@Autowired
//	Constants constants;
	Document doc;
	Constants constants = new ConstantsTestImpl();
	
	
	public  MenuUtil() throws Exception{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		//docFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		doc = docBuilder.parse(constants.getRepoPath()+"\\templates\\Schema.xml");
	}
	
	
	public NodeList getByQuery(String expression) throws Exception{
		
		//"//Worksheet[@Name=\"" +sheetName+"\"]/Table/Row[1]/Cell"
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(expression);
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList headerNodes = (NodeList) result;
		expression = "//does";
		Node toRem = (Node)headerNodes.item(0);
		return headerNodes;
	}
	

	
	public List<Menu> getSubMenuList(String expression) throws Exception{
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(expression);
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList headerNodes = (NodeList) result;
		List<Menu> menuList = new ArrayList<Menu>();
		for(int i  = 0;i <headerNodes.getLength();i++){
			Node itemNode = headerNodes.item(i);
			String menuLbl = itemNode.getAttributes().getNamedItem("nl").getTextContent();
			Menu subMenu = new Menu();
			subMenu.setLabel(menuLbl);
			menuList.add(subMenu);
		}
		System.out.println("processed "+expression +"  Returned "+menuList.size());
		return menuList;
	}
	
	
	public Menu homePageMenu() throws Exception{
		Menu menu = new Menu();
		menu.setLabel("Home");
		List<Menu> menuList = getSubMenuList("//does");
		for(Menu m1:menuList) {
			String lbl = m1.getLabel();
			List<Menu> menuListL2 = getSubMenuList("//does[@nl=\""+lbl+"\"]/intent/strategy/theme");
			for(Menu m2:menuListL2) {
				String lbl2 = m2.getLabel();
				List<Menu> menuListL3 = getSubMenuList("//does[@nl=\""+lbl+"\"]/intent/strategy/theme[@nl=\""+lbl2+"\"]/visit");
				m2.setSubMenuItems(menuListL3);
			}
			m1.setSubMenuItems(menuListL2);
		}
		menu.setSubMenuItems(menuList);
		System.out.println(menu);
		return menu;
	}
	
	
	
}
