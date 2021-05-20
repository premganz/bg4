package org.spo.cms3.svc;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.ifs3.template.web.Constants;
import org.spo.svc3.config.AppConstants;
import org.spo.svc3.trx.pgs.mdl.ActionAssembly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class PageService {
	@Autowired
	private Constants constants;
	String filePath;
	String fullPathMeta;

	{

//		filePath=constants.getRepoPath();
		
	}


	//private String dataRootDir ;
	private Log log = LogFactory.getLog(this.getClass().getName());
	private static final Logger logger = LoggerFactory.getLogger(PageService.class);

	public PageService(String rootDir){
	}


	public PageService(){
	}



	boolean testMode=false;
	//scenario = doesDir/domainDir
//	public String readUpPage(String scenario, String pageName){
//		File f = null;
//		log.debug("attempting to read page ");
//		logger.error("attempting to read page "+constants.getRepoPath()+"/"+scenario+"/"+pageName+".txt");
//		return readUpPageUtils(f);
//	}

	private String getPath(ActionAssembly assem, String repoPath) {
		
		String path=repoPath+"/content/"+assem.getMajorCode()+"/";
		if (assem.getMinorCode().equals("")) {
			path=path+"index.txt";
			return path;
		}else {
			path=path+assem.getMinorCode()+"/";
		}
		if (assem.getActionCode().equals("")) {
			path=path+"index.txt";
			return path;
		}else {
			path=path+assem.getActionCode()+"/";
		}
		if (assem.getReadableMatter().equals("")) {
			path=path+"index.txt";
			return path;
		}else {
			path=path+assem.getReadableMatter();
		}
			return path;
	}
	
	public String readUpPage(ActionAssembly assem){
		File f = null;
		String repoPath=constants.getRepoPath();
		String path=getPath(assem,repoPath);
		log.debug("attempting to read page ");
		logger.error("attempting to read page "+ path);
		f= new File(path);
		String bufContent = readUpPageUtils(f);
		String contentToReturn = "";
		if(bufContent.contains(AppConstants.CONTENT_SEPERATOR)) {
			contentToReturn = bufContent.split(AppConstants.CONTENT_SEPERATOR)[0];	
		}else {
			contentToReturn = bufContent;
		}
		return contentToReturn;
	}
	public String readUpPageMeta(ActionAssembly assem){
		File f = null;
		String repoPath=constants.getRepoPath();
		String path=getPath(assem,repoPath);
		log.debug("attempting to read page ");
		logger.error("attempting to read page "+ path);
		f= new File(path);
		String bufContent = readUpPageUtils(f);
		String contentToReturn = "";
		if(bufContent.contains(AppConstants.CONTENT_SEPERATOR) && bufContent.split(AppConstants.CONTENT_SEPERATOR).length>1) {
			contentToReturn = bufContent.split(AppConstants.CONTENT_SEPERATOR)[1];	
		}else {
			contentToReturn = bufContent;
		}
		return contentToReturn;
	}

	
	
	
	public String readUpPageStaging(ActionAssembly assem){
		File f = null;
		String repoPath=constants.getRepoPath();
		repoPath=constants.getRepoPath().replaceAll("cms1", "cms-staging");
		String path=getPath(assem,repoPath);
		log.debug("attempting to read page ");
		logger.error("attempting to read page "+ path);
		f= new File(path);
		String bufContent = readUpPageUtils(f);
		String contentToReturn = "";
		if(bufContent.contains(AppConstants.CONTENT_SEPERATOR)) {
			contentToReturn = bufContent.split(AppConstants.CONTENT_SEPERATOR)[0];	
		}else {
			contentToReturn = bufContent;
		}
		return contentToReturn;
	}
	
	public String readUpPageStagingMeta(ActionAssembly assem){
		File f = null;
		String repoPath=constants.getRepoPath();
		repoPath=constants.getRepoPath().replaceAll("cms1", "cms-staging");
		String path=getPath(assem,repoPath);
		log.debug("attempting to read page ");
		logger.error("attempting to read page "+ path);
		f= new File(path);
		String bufContent = readUpPageUtils(f);
		String contentToReturn = "";
		if(bufContent.contains(AppConstants.CONTENT_SEPERATOR) && bufContent.split(AppConstants.CONTENT_SEPERATOR).length>1) {
			contentToReturn = bufContent.split(AppConstants.CONTENT_SEPERATOR)[1];	
		}else {
			contentToReturn = bufContent;
		}
		return contentToReturn;
	}
	
	public void createFile(ActionAssembly assem){
		File f = null;
		String repoPath=constants.getRepoPath();
		String path=getPath(assem,repoPath);
		repoPath=constants.getRepoPath().replaceAll("cms1", "cms-staging");
		String staging_path=getPath(assem,repoPath);
		log.debug("attempting to read page ");
		logger.error("attempting to create page "+ path);
		f= new File(path);File f_staging = new File(staging_path);
		try {
			if(!f.exists()) 
			f.createNewFile();
			if(!f_staging.exists())
			f_staging.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void writePage(String fileName, String content, String metaContent, boolean publishNow){
		File f = null;
		StringBuffer buf = new StringBuffer();
		URL resourceUrl = getClass().getResource("/posts/HelloWorld.html");
		BufferedWriter writerBuf = null;
		String repoPath=constants.getRepoPath();
		if(!publishNow) {
			repoPath=constants.getRepoPath().replaceAll("cms1", "cms-staging");
		}
		System.out.println("writing to file "+constants.getRepoPath()+"/"+fileName);
		f= new File(repoPath+"/content/"+fileName);
		FileWriter writer;
		
		try {
			writer= new FileWriter(f);
			writerBuf = new BufferedWriter(writer);
			writerBuf.write(content+AppConstants.CONTENT_SEPERATOR+metaContent);


		} catch (IOException e1) {
			buf.append("FILE not found");
			e1.printStackTrace();
		}
		finally{
			try {
				writerBuf.close();
			} catch (IOException e) {
				e.printStackTrace();
				buf.append("ERROR");
			}
		}


	}

	
	public List<String> getListBoxes(String major, String minor) throws Exception{

		ArrayList<String> resultList = new ArrayList<String>();
		String resourcePath="";
		if(minor!=null) {
			resourcePath=constants.getRepoPath()+"/"+major+"/"+minor;
		}else {
			resourcePath=constants.getRepoPath()+"/"+major;
		}

		File[] fileArray = new File(resourcePath).listFiles();
		for(File f1:fileArray) {
			resultList.add(f1.getName());
		}
		return resultList;
	}
	
	
	
	

	public String dumpFilesData(){
		StringBuffer buf = new StringBuffer();
		String folderName=constants.getRepoPath()+"/"+"augment/";
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				File f = new File(folderName+listOfFiles[i].getName());
				System.out.println("File " + listOfFiles[i].getName());
				buf.append(readUpPageUtils(f));
				buf.append('\n'+"++++++EOF++++++: "+f.getName()+'\n');
			} 
		}
		buf.append("++++++SECTION++++++: POSTS");
		folderName = constants.getRepoPath()+"/"+"content/";
		folder = new File(folderName);
		listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				File f = new File(folderName+listOfFiles[i].getName());
				System.out.println("File " + listOfFiles[i].getName());
				buf.append(readUpPageUtils(f));
				buf.append('\n'+"++++++EOF++++++: "+f.getName()+'\n');
			} 
		}
		return buf.toString();

	}

	public List<String> readFileCatalog(String major, String minor, String action, String article){
		File f = null;

		ArrayList<String> resultList = new ArrayList<String>();
		String resourcePath="";
		if(article!=null) {
			resourcePath=constants.getRepoPath()+"/"+major+"/"+minor+"/"+action+"/"+article;
		}
		else if(action!=null) {
			resourcePath=constants.getRepoPath()+"/"+major+"/"+minor+"/"+action;
		}
		else if(minor!=null) {
			resourcePath=constants.getRepoPath()+"/"+major+"/"+minor;
		}else {
			resourcePath=constants.getRepoPath()+"/"+major;
		}

		File[] fileArray = new File(resourcePath).listFiles();
		for(File f1:fileArray) {
			resultList.add(f1.getName());
		}
		return resultList;
	}


	private String readUpPageUtils(File f){

		StringBuffer buf = new StringBuffer();
		FileReader reader;
		try {
			reader = new FileReader(f);
			BufferedReader readerBuf = new BufferedReader(reader);
			try {
				System.out.println("Trying to read path : "+f.getAbsolutePath()+"name: "+f.getName());
				String line = readerBuf.readLine();
				while(line!=null){
					buf.append(line);
					//buf.append("</br>");
					line = readerBuf.readLine();
				}

			} catch (Exception  e) {
				e.printStackTrace();
				buf.append("ERROR");
			}finally{
				try {
					readerBuf.close();
				} catch (IOException e) {
					e.printStackTrace();
					buf.append("ERROR");
				}
			}
		} catch (FileNotFoundException e1) {
			buf.append("FILE not found");
			e1.printStackTrace();
		}


		if(buf.toString().isEmpty()){
			buf.append("BLANK");
			buf.append("***EOL***");
		}
		String bufContent = buf.toString();
		return bufContent;

	}


	public Constants getConstants() {
		return constants;
	}


	public void setConstants(Constants constants) {
		this.constants = constants;
	}








}