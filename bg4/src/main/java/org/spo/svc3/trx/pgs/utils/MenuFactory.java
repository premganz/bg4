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
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.spo.ifs3.template.web.Constants;
import org.spo.svc3.trx.def.ConstantsTestImpl;
import org.spo.svc3.trx.pgs.mdl.Menu;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MenuFactory {
//	@Autowired
//	Constants constants;
	static Document doc;
	Constants constants = new ConstantsTestImpl();
	
	
	public  MenuFactory() throws Exception{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		//docFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//		doc = docBuilder.parse(constants.getRepoPath()+"/templates/Schema.xml");
		doc = docBuilder.parse(constants.getRepoPath()+"/content/meta/meta-schema/Schema_cms.xml");
	}
	
	
	public  NodeList getByQuery(String expression) throws Exception{
		
		//"//Worksheet[@Name=\"" +sheetName+"\"]/Table/Row[1]/Cell"
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(expression);
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList headerNodes = (NodeList) result;
		expression = "//does";
		Node toRem = (Node)headerNodes.item(0);
		return headerNodes;
	}
	

	
	public  List<Menu> getSubMenuList(String expression) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr=null;
		try {
			expr = xpath.compile(expression);
		} catch (XPathExpressionException e) {
			
			e.printStackTrace();
		}
		Object result = null;
		try {
			result = expr.evaluate(doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		NodeList headerNodes = (NodeList) result;
		List<Menu> menuList = new ArrayList<Menu>();
		for(int i  = 0;i <headerNodes.getLength();i++){
			Node itemNode = headerNodes.item(i);
			String menuLbl = itemNode.getAttributes().getNamedItem("lbl")!=null?
					itemNode.getAttributes().getNamedItem("lbl").getTextContent():
						itemNode.getAttributes().getNamedItem("id").getTextContent();
			
			String nl = itemNode.getAttributes().getNamedItem("nl")!=null?
					itemNode.getAttributes().getNamedItem("nl").getTextContent():						"";
			String id = itemNode.getAttributes().getNamedItem("id").getTextContent();
			String nlId = nl.replaceAll(" ", "_");
			
			Menu subMenu = new Menu();
			subMenu.setLbl(menuLbl);
			subMenu.setNl(nl);
			subMenu.setNlId(nlId);
			subMenu.setId(id);
			menuList.add(subMenu);
		}
		System.out.println("processed "+expression +"  Returned "+menuList.size());
		return menuList;
	}
	
	
//	public  Menu homePageMenu() throws Exception{
//		Menu menu = new Menu();
//		List<Menu> menuList = getSubMenuList("//role");
//		System.out.println(menu);
//		return menu;
//	}
	
	
	public  Menu homePageMenu() throws Exception{
		Menu menu = new Menu();
		menu.setNl("Home");
		menu.setLbl("Home");
		menu.setClickable(false);
		menu.setLevelCd("home");
		List<Menu> menuList = getSubMenuList("//major");
		for(Menu m0:menuList) {
			m0.setLevelCd("major");
			String id = m0.getId();
			List<Menu> menuList0 = getSubMenuList("//major[@id=\""+id+"\"]/minor");
			for(Menu m1:menuList0) {
				m1.setLevelCd("minor");
				String minor_id = m1.getId();
				List<Menu> menuListL2 = getSubMenuList("//minor[@id=\""+minor_id+"\"]/action");
				for(Menu m2:menuListL2) {
					m2.setLevelCd("action");
					String action_id = m2.getId(); //xpath is probably incorrect
					List<Menu> menuListL3 = getSubMenuList("//major[@id=\""+minor_id+"\"]/minor[@id=\""+action_id+"\"]/article");
					for(Menu m3:menuListL3) {
						m3.setLevelCd("article");
					}
					m2.setSubMenuItems(menuListL3);
				}
				m1.setSubMenuItems(menuListL2);
			}
			m0.setSubMenuItems(menuList0);
		}
		menu.setSubMenuItems(menuList);
		System.out.println(menu);
		return menu;
	}
	
	public  Menu subPageMenu(String majorId) throws Exception{
		Menu menu = new Menu();
		menu.setNl("Home");
		menu.setLbl("Home");
		menu.setClickable(false);
		menu.setLevelCd("nonClickable");
		List<Menu> menuList = getSubMenuList("//major/minor[@id=\""+majorId+"\"]/../minor");
		for(Menu m0:menuList) {
			m0.setLevelCd("minor");
			String lbl = m0.getId();
			List<Menu> menuList0 = getSubMenuList("//minor[@id=\""+lbl+"\"]/action");
			for(Menu m1:menuList0) {
				m1.setLevelCd("action");
				String nl = m1.getId();
				List<Menu> menuListL2 = getSubMenuList("//action[@id=\""+nl+"\"]/article");
				for(Menu m2:menuListL2) {
					m2.setLevelCd("article");
					String nl2 = m2.getId();
					List<Menu> menuListL3 = getSubMenuList("//major[@id=\""+nl+"\"]/minor[@id=\""+nl2+"\"]/article");
					for(Menu m3:menuListL3) {
						m3.setLevelCd("article");
					}
					m2.setSubMenuItems(menuListL3);
				}
				m1.setSubMenuItems(menuListL2);
			}
			m0.setSubMenuItems(menuList0);
		}
		menu.setSubMenuItems(menuList);
		System.out.println(menu);
		return menu;
	}
	
	
	
//	public  Menu homePageMenuDoes(String id) throws Exception{
//		Menu menu = new Menu();
//		menu.setNl("Home");
//		menu.setLbl("Home");
//		menu.setClickable(false);
//		menu.setLevelCd("nonClickable");
//		List<Menu> menuList = getSubMenuList("//does[@id=\""+id+"\"]");
//		for(Menu m1:menuList) {
//			m1.setLevelCd("does");
//			String nl = m1.getNl();
//			List<Menu> menuListL2 = getSubMenuList("//does[@nl=\""+nl+"\"]/intent/strategy/theme");
//			for(Menu m2:menuListL2) {
//				m2.setLevelCd("theme");
//				String nl2 = m2.getNl();
//				List<Menu> menuListL3 = getSubMenuList("//does[@nl=\""+nl+"\"]/intent/strategy/theme[@nl=\""+nl2+"\"]/visit");
//				for(Menu m3:menuListL3) {
//					m3.setLevelCd("visit");
//				}
//				m2.setSubMenuItems(menuListL3);
//			}
//			m1.setSubMenuItems(menuListL2);
//		}
//		menu.setSubMenuItems(menuList);
////		System.out.println(menu);
//		return menu;
//	}
	
	
	
//	public  Menu deriveQueryMenu(String actionPageId) throws Exception{
//		Menu menu = new Menu();
//		menu.setLbl("Home");
//		List<Menu> menuList = getSubMenuList("//action[@id=\""+actionPageId+"\"]/query");
//		for(Menu m1:menuList) {
//			m1.setLevelCd("does");
//			String lbl = m1.getId();
//			List<Menu> menuListL2 = getSubMenuList("//does[@id=\""+lbl+"\"]/intent/strategy/theme");
//			for(Menu m2:menuListL2) {
//				m2.setLevelCd("theme");
//				String lbl2 = m2.getId();
//				List<Menu> menuListL3 = getSubMenuList("//does[@nl=\""+lbl+"\"]/intent/strategy/theme[@nl=\""+lbl2+"\"]/visit");
//				for(Menu m3:menuListL3) {
//					m3.setLevelCd("visit");
//				}
//				m2.setSubMenuItems(menuListL3);
//			}
//			m1.setSubMenuItems(menuListL2);
//		}
//		menu.setSubMenuItems(menuList);
//		System.out.println(menu);
//		return menu;
//	}
	
	
	
	
//	public static HomeItf getJsonFragFrmXml() throws Exception{
//		HomeItf pg = new HomeItf();
//		pg.setHomePageContentId("home_welcome");
//		
//		List<Menu> menuList = getSubMenuList("//does");
//		for(Menu m1:menuList) {
//			String lbl = m1.getLbl();
//			List<Menu> menuListL2 = getSubMenuList("//does[@nl=\""+lbl+"\"]/intent/strategy/theme");
//			for(Menu m2:menuListL2) {
//				String lbl2 = m2.getLbl();
//				List<Menu> menuListL3 = getSubMenuList("//does[@nl=\""+lbl+"\"]/intent/strategy/theme[@nl=\""+lbl2+"\"]/visit");
//				m2.setSubMenuItems(menuListL3);
//			}
//			m1.setSubMenuItems(menuListL2);
//		}
//		menu.setSubMenuItems(menuList);
//		System.out.println(menu);
//		return null;
//	}
	
}
