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
import org.spo.svc3.trx.pgs.mdl.ActionAssembly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class PageService {
	@Autowired
	private Constants constants;
//	String filePath;
	String fullPathMeta;

	

	//private String dataRootDir ;
	private Log log = LogFactory.getLog(this.getClass().getName());
	private static final Logger logger = LoggerFactory.getLogger(PageService.class);

	public PageService(String rootDir){
	}


	public PageService(){
	}



	boolean testMode=false;
	//scenario = doesDir/domainDir
	@Deprecated
	public String readUpPage(String pageName){
		String fileNameWithPath=constants.getRepoPath()+"/"+pageName+".txt";
		File f = new File(fileNameWithPath);
		log.debug("attempting to read page ");
		logger.error("attempting to read page "+fileNameWithPath);
		return readUpPageUtils(f);
	}
	@Deprecated
	public String readUpPage(String scenario, String pageName){
		return readUpPage(pageName);
	}

	public String readUpPage(ActionAssembly assem, boolean isContent){
		File f = null;
		String pathPrefix;
		if(isContent) {
			pathPrefix=constants.getRepoPath()+"/content";
		}else {
			pathPrefix=constants.getRepoPath()+"/content";
		}
		String path=pathPrefix+"/"+assem.getDoesCode()+"/"+assem.getDomainCode()+"/"+assem.getActionCode()+".txt";
		log.debug("attempting to read page ");
		logger.error("attempting to read page "+ path);
		f= new File(path);
		return readUpPageUtils(f);
	}


	public String writePage(ActionAssembly assem, String contentToWrite, boolean isContent){
		File f = null;
		String pathPrefix;
		if(isContent) {
			pathPrefix=constants.getRepoPath()+"/content";
		}else {
			pathPrefix=constants.getRepoPath()+"/content";
		}
		String path=pathPrefix+"/"+assem.getDoesCode()+"/"+assem.getDomainCode()+"/"+assem.getActionCode();
		log.debug("attempting to read page ");
		logger.error("attempting to read page "+ path);
		f= new File(path);
		writePage(path,contentToWrite );
		return "";
	}
	
	@Deprecated
	public void writePage(String fileName, String content){
		File f = null;
		StringBuffer buf = new StringBuffer();
		URL resourceUrl = getClass().getResource("/posts/HelloWorld.html");
		BufferedWriter writerBuf = null;
		System.out.println("writing to file ::"+fileName);
		f= new File(fileName);
		FileWriter writer;
		try {
			writer= new FileWriter(f);
			writerBuf = new BufferedWriter(writer);

			writerBuf.write(content);


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

	
	public List<String> getListBoxes(String does, String theme) throws Exception{

		ArrayList<String> resultList = new ArrayList<String>();
		String resourcePath="";
		if(theme!=null) {
			resourcePath=constants.getRepoPath()+"/"+does+"/"+theme;
		}else {
			resourcePath=constants.getRepoPath()+"/"+does;
		}

		File[] fileArray = new File(resourcePath).listFiles();
		for(File f1:fileArray) {
			resultList.add(f1.getName());
		}
		return resultList;
	}
	
	
	
	public List<String> readUpPageList(String scenario, String pageName){

		File f = null;
		ArrayList<String> resultList = new ArrayList<String>();
		URL resourceUrl = getClass().getResource("/"+scenario+"/"+pageName+".txt");
		String resourcePath;
		try {
			resourcePath = resourceUrl.toURI().getPath();
			f= new File(resourcePath);
			FileReader reader = new FileReader(f);
			BufferedReader readerBuf = new BufferedReader(reader);
			String line = readerBuf.readLine();
			while(line!=null){
				resultList.add(line);
				line = readerBuf.readLine();
			}

		} catch (Exception  e) {
			e.printStackTrace();
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

	public List<String> readFileCatalog(String does, String theme){
		File f = null;

		ArrayList<String> resultList = new ArrayList<String>();
		String resourcePath="";
		if(theme!=null) {
			resourcePath=constants.getRepoPath()+"/"+does+"/"+theme;
		}else {
			resourcePath=constants.getRepoPath()+"/"+does;
		}

		File[] fileArray = new File(resourcePath).listFiles();
		for(File f1:fileArray) {
			resultList.add(f1.getName());
		}
		return resultList;
	}
	
	public List<String> getDoesList(){
		ArrayList<String> resultList = new ArrayList<String>();
		String resourcePath="";
		resourcePath=constants.getRepoPath()+"/content/";
		File[] fileArray = new File(resourcePath).listFiles();
		for(File f1:fileArray) {
			resultList.add(f1.getName());
		}
		return resultList;
	}

	public List<String> getThemeList(String does){
		ArrayList<String> resultList = new ArrayList<String>();
		String resourcePath="";
		resourcePath=constants.getRepoPath()+"/content/"+"/"+does+"/";
		File[] fileArray = new File(resourcePath).listFiles();
		for(File f1:fileArray) {
			resultList.add(f1.getName());
		}
		return resultList;
	}

	public List<String> getArticlesList(String does, String theme){
		ArrayList<String> resultList = new ArrayList<String>();
		String resourcePath="";
		resourcePath=constants.getRepoPath()+"/content/"+"/"+does+"/"+"/"+theme+"/";
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
		return buf.toString();

	}


	public Constants getConstants() {
		return constants;
	}


	public void setConstants(Constants constants) {
		this.constants = constants;
	}








}