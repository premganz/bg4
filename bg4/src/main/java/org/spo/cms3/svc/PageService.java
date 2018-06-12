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

import org.spo.ifs3.template.web.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PageService {
	@Autowired
	private Constants constants;

	//private String dataRootDir ;
	
	public PageService(String rootDir){
	}
	
	
	public PageService(){
	}
	
boolean testMode=false;
	public String readUpPage(String scenario, String pageName){
		File f = null;
		if(scenario!=null){
			f= new File(constants.getRepoPath()+"/"+scenario+"/"+pageName+".txt");
		}else{
			f= new File(constants.getRepoPath()+"/"+pageName+".txt");
		}
		return readUpPageUtils(f);
	}
	
	

	
	public void writePage(String fileName, String content){
		File f = null;
		StringBuffer buf = new StringBuffer();
		URL resourceUrl = getClass().getResource("/posts/HelloWorld.html");
		
		
		BufferedWriter writerBuf = null;
		System.out.println("writing to file "+constants.getRepoPath()+"/"+fileName+".txt");
			f= new File(constants.getRepoPath()+"/"+fileName+".txt");
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
		String folderName=constants.getRepoPath()+"/"+"templates/";
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
		    folderName = constants.getRepoPath()+"/"+"posts/";
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
	
	public List<String> readFileCatalog(String scenario){
		File f = null;
		ArrayList<String> resultList = new ArrayList<String>();
		f= new File(constants.getRepoPath()+"/"+scenario);

		File[] filesList = f.listFiles();
		for (File file : filesList) {		
			if (file.isFile() && !file.getName().endsWith("_meta.txt")) {				
				resultList.add(file.getName().replaceAll(".txt",""));
			}
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