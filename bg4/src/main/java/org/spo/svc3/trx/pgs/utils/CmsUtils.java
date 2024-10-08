package org.spo.svc3.trx.pgs.utils;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.spo.ifs3.template.web.Constants;
import org.spo.svc3.trx.def.ConstantsTestImpl;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
@Component
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
		doc = docBuilder.parse(constants.getRepoPath()+"/content/meta/meta-schema/Schema_cms.xml");
	}


	//Run once to create folders
	public  void organizeFolders() {
		String xquery= "//vsn" ;
		try {
			organizeFoldersHelper1 (util_queryHelper1(xquery),"","","", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Run once to create folders
	public  void organizeFoldersMeta() {
		String xquery= "//vsn" ;
		try {
			organizeFoldersHelper1 (util_queryHelper1(xquery),"","", "",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


//	public  String formatXml(String unformattedXml) {
//		String x="ERROR";
//
//		try {
//
//			OutputFormat format = new OutputFormat(doc);
//			format.setLineWidth(65);
//			format.setIndenting(true);
//			format.setIndent(2);
//			Writer out = new StringWriter();
//			XMLSerializer serializer = new XMLSerializer(out, format);
//			serializer.serialize(doc);
//
//			x= out.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return x;
//	}

	private  void organizeFoldersHelper1(Element node, String currentStrategyDir, String currentDomainDir,String currentActionDir, boolean isMeta) throws Exception {
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
					String actionCode= currentNode.getAttributes().getNamedItem("type").getTextContent();
					if(!dir.exists()) {
						dir.mkdirs();
					}
					File indexFileMajor = new File(cmsDir+currentStrategyDir+"/index.txt");
					File indexFileMinor = new File(cmsDir+currentStrategyDir+"/"+currentDomainDir+"/index.txt");
					if(dir.exists() && "del".equals(actionCode)) {
						dir.renameTo(new File(constants.getRepoPath()+"/deleted/"+dir.getName()+Calendar.getInstance().getTimeInMillis()));
						FileUtils.deleteQuietly(dir) ;
					}
					
					else if(!indexFileMajor.exists()) {
						indexFileMajor.createNewFile();
					}
				
					else if(!indexFileMinor.exists()) {
						indexFileMinor.createNewFile();
					}
					
				}
				if(currentNode.getNodeName().equals("action")) {
					currentActionDir= currentNode.getAttributes().getNamedItem("id").getTextContent().replaceAll(" ", "_");
					String actionCode= currentNode.getAttributes().getNamedItem("type").getTextContent();
					File dir = new File (cmsDir+currentStrategyDir+"/"+currentDomainDir+"/"+currentActionDir);
					File indexFileAction = new File(cmsDir+currentStrategyDir+"/"+currentDomainDir+"/"+currentActionDir+"/index.txt");
					if(!dir.exists()) {
						dir.mkdirs();
					}
					if(dir.exists() && "del".equals(actionCode)) {
						dir.renameTo(new File(constants.getRepoPath()+"/deleted/"+dir.getName()+Calendar.getInstance().getTimeInMillis()));
						FileUtils.deleteQuietly(dir) ;
					}
					
					else if(!indexFileAction.exists()) {
						indexFileAction.createNewFile();
					}
				
				}
				if(currentNode.getNodeName().equals("article")) {
					System.out.println("got an article ");
					String articlePath = cmsDir+currentStrategyDir+"/"+currentDomainDir+"/"+currentActionDir+"/";
					String articleName = currentNode.getAttributes().getNamedItem("id").getTextContent();
					String actionCode= currentNode.getAttributes().getNamedItem("type").getTextContent();
					String articleFilePath = articlePath+articleName+extn;
					File file = new File (articleFilePath);
					if(!file.exists() && actionCode.equals("liv")) {
						log.debug("creating file "+file.getName());
						file.createNewFile();
					}
					else if(file.exists() && actionCode.equals("del")) {
						FileUtils.copyFile(new File(articleFilePath) , new File(articleFilePath+".bck"));
						FileUtils.deleteQuietly(new File(articleFilePath) );
					}
				}
				
				//				if(currentNode instanceof DeferredElementImpl){
				//					continue;
				//				} 
//				
				if(currentNode.hasChildNodes()) {
					organizeFoldersHelper1((Element)currentNode,  currentStrategyDir, currentDomainDir, currentActionDir, isMeta);
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
		//		cmsUtil.doc=docBuilder.parse("C:/Users/gs1-premg/git/bg4-2/bg4/src/main/resources/data-cms/cms1/content/Schema_cms.xml");
		//				"C:/Users/premganesh/git/bg4/bg4/src/test/resources/ApplicationNavTreeModelGeneric.xml");
		cmsUtil.organizeFolders();

		//		cmsUtil.organizeFoldersMeta();
	}


}
