package org.spo.cms4.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.cms4.model.QMessage;
import org.spo.cms4.svc.PageService;
import org.spo.cms4.svc.SocketConnector;
import org.spo.ifs4.template.web.Constants2;
import org.spo.svc4.trx.pgs.utils.CmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WriteBackController {

	@Autowired
	private Constants2 constants;
	private static final Logger logger = LoggerFactory
			.getLogger(WriteBackController.class);

	@Autowired
	public CmsUtils cmsUtils;


	private SocketConnector connector=new SocketConnector();


	@RequestMapping(value = "87438300874writeback/entry", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		PostContent content1 = new PostContent();
		content1.setHtmlContent("hello");
		model.addAttribute("content", content1);
		
		
		return "cms1/writeback";
	}
	


	@RequestMapping(value="87438300874writeback/contentSubmit")
	public String processContent(
			final PostContent content, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			content.setFormErrors(bindingResult.getAllErrors().toString());
			model.addAttribute("content", content);
			return "redirect:entry";
		}

		System.out.println(content.getHtmlContent());
		logger.info("Writing "+content.getMeta()  );
		QMessage message = new QMessage();
		message.setHandler("write");
		message.setContent(content.getHtmlContent());
		message.setMeta(content.getMeta());
		String response ="";
		try {
			String pathToWrite ="About/about/contact/index.txt";
			writeResponse(pathToWrite,content.getHtmlContent(),
					content.getMeta(),true);


			//				svc.writePage(, content.getHtmlContent());
			//				svc.writePage("posts/"+content.getId()+"_meta", content.getMeta());
			//response = connector.getResponse(message);
			//TextMessage reply = sender.simpleSend(message.toString()); 
			//response=reply.getText();

		} catch (Exception e) {			
			e.printStackTrace();
		}
		content.setFormErrors("Success");		
		content.setHtmlContent("");		
		model.clear();
		model.addAttribute("content", content);

		// model.clear();
		return "redirect:entry";
	}
	
	private String getPath(PostContent content) {
		String path=content.getPath1();
		if(content.getPath2().contentEquals("index.txt")) {
			path = path+"/"+content.getPath2();
			return path;
		}else if(!content.getPath2().isEmpty()){
			path=path+"/"+content.getPath2();
		}
		if(content.getPath3().contentEquals("index.txt")) {
			path = path+"/"+content.getPath3();
			return path;
		}else if(!content.getPath3().isEmpty()){
			path=path+"/"+content.getPath3();
		}
		if(content.getPath4().contentEquals("index.txt")) {
			path = path+"/"+content.getPath4();
			return path;
		}else if(!content.getPath4().isEmpty()){
			path=path+"/"+content.getPath4();
		}	
			
	    return path;
		
	}
	
	public void writeResponse(String fileName, String content, String metaContent, boolean publishNow) throws Exception{
		File f = null;
		String repoPath=constants.getRepoPath();
		System.out.println("writing to file "+constants.getRepoPath()+"/content/"+fileName);
		f= new File(constants.getRepoPath()+"/content/"+fileName);
		
		if(FileUtils.readFileToString(f).length()>100000) throw new Exception(); 
		
		  
		try {
			
			FileUtils.write(f, content, true);


		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
