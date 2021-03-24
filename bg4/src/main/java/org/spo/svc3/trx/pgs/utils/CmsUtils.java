package org.spo.svc3.trx.pgs.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.spo.ifs3.template.web.Constants;
import org.spo.svc3.trx.def.ConstantsTestImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CmsUtils {
	public static Document doc;
	
	Constants constants = new ConstantsTestImpl();
	String cmsDir = constants.getRepoPath()+"/"+"content"+"/";
	String cmsMetaDir = constants.getRepoPath()+"/augment/";
	Log log = LogFactory.getLog(CmsUtils.class);

	public  CmsUtils() throws Exception{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		//docFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		doc = docBuilder.parse(constants.getRepoPath()+"\\content\\meta\\meta-schema\\Schema_cms.xml");
		

	}

	//Run once to create folders
	public  void organizeFolders() {
		String xquery= "//vsn" ;
		try {
			organizeFoldersHelper1 (util_queryHelper1(xquery),"","", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Run once to create folders
		public  void organizeFoldersMeta() {
			String xquery= "//vsn" ;
			try {
				organizeFoldersHelper1 (util_queryHelper1(xquery),"","", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	private  void organizeFoldersHelper1(Element node, String currentStrategyDir, String currentDomainDir, boolean isMeta) throws Exception {
		// do something with the current node instead of System.out
		String cmsPath = isMeta?cmsDir:cmsMetaDir;
		String extn = isMeta?"_augm.txt":".txt";
		System.out.println(node.getNodeName());
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				if(currentNode.getNodeName().equals("major")) {
					currentStrategyDir= currentNode.getAttributes().getNamedItem("id").getTextContent();
					File dir = new File (cmsPath+currentStrategyDir);
					if(!dir.exists()) {
						log.debug("creating dir "+dir.getAbsolutePath());
						dir.mkdirs();
					}
				}
				if(currentNode.getNodeName().equals("minor")) {
					currentDomainDir= currentNode.getAttributes().getNamedItem("id").getTextContent();
					File dir = new File (cmsDir+currentStrategyDir+"/"+currentDomainDir);
					if(!dir.exists()) {
						dir.mkdirs();
					}
					File indexFileMajor = new File(cmsDir+currentStrategyDir+"/index.txt");
					if(!indexFileMajor.exists()) {
						indexFileMajor.createNewFile();
					}
					File indexFileMinor = new File(cmsDir+currentStrategyDir+"/"+currentDomainDir+"/index.txt");
					if(!indexFileMinor.exists()) {
						indexFileMinor.createNewFile();
					}
				}
				if(currentNode.getNodeName().equals("action")) {
					File file = new File (cmsDir+currentStrategyDir+"/"+currentDomainDir+"/"+currentNode.getAttributes().getNamedItem("id").getTextContent().replaceAll(" ", "_")+extn);
					if(!file.exists()) {
						log.debug("creating file "+file.getName());
						file.createNewFile();
					}
				}
//				if(currentNode instanceof DeferredElementImpl){
//					continue;
//				} 
				if(currentNode.hasChildNodes()) {
					organizeFoldersHelper1((Element)currentNode, currentStrategyDir, currentDomainDir, isMeta);
				}
			}
		}

	}


//	private  void organizeFoldersHelper(Node node) throws Exception {
//		// do something with the current node instead of System.out
//		System.out.println(node.getNodeName());
//		String currentStrategyDir="";
//		String currentDomainDir="";
//		NodeList nodeList = node.getChildNodes();
//		for (int i = 0; i < nodeList.getLength(); i++) {
//			Node currentNode = nodeList.item(i);
//			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
//				if(currentNode.getNodeName().equals("major")) {
//					currentStrategyDir= currentNode.getAttributes().getNamedItem("id").getTextContent();
//					File dir = new File (cmsDir+currentStrategyDir);
//					if(!dir.exists()) {
//						dir.mkdirs();
//					}
//					
//				}
//				if(currentNode.getNodeName().equals("minor")) {
//					currentDomainDir= currentNode.getAttributes().getNamedItem("id").getTextContent();
//					File dir = new File (cmsDir+currentStrategyDir+"/"+currentDomainDir);
//					if(!dir.exists()) {
//						dir.mkdirs();
//					}
//				}
//				if(currentNode.getNodeName().equals("action")) {
//					File file = new File (cmsDir+currentStrategyDir+"/"+currentDomainDir+"/"+currentNode.getAttributes().getNamedItem("id").getTextContent()+".txt");
//					if(!file.exists()) {
//						file.createNewFile();
//					}
//				}
//			}
//		}
//
//	}


	public NodeList util_queryHelper(String expression) throws Exception{
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(expression);
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList headerNodes = (NodeList) result;
		System.out.println("processed "+expression +"  Returned "+headerNodes.getLength());
		return headerNodes;
	}
	
	
	public Element util_queryHelper1(String xquery) throws Exception{
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		//		xquery= "//page[@name='AbstractHomePage']" ;
		XPathExpression expr = xpath.compile(xquery );
		Object result = expr.evaluate( doc, XPathConstants.NODE );
		Node node = (Node) result;
		List<Element> heirarchialElems = new ArrayList<Element>();
		node.getChildNodes();
		return (Element)node;
		
	}

	//TODO Run with great care, will erase data.
	public static void main(String[] args) throws Exception{
		CmsUtils cmsUtil = new CmsUtils();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		//docFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//		cmsUtil.doc=docBuilder.parse("C:\\Users\\gs1-premg\\git\\bg4-2\\bg4\\src\\main\\resources\\data-cms\\cms1\\content\\Schema_cms.xml");
//				"C:\\Users\\premganesh\\git\\bg4\\bg4\\src\\test\\resources\\ApplicationNavTreeModelGeneric.xml");
		cmsUtil.organizeFolders();
//		cmsUtil.organizeFoldersMeta();
	}
	
	
}
