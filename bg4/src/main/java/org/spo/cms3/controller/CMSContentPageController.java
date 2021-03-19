package org.spo.cms3.controller;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.cms3.model.QMessage;
import org.spo.cms3.svc.PageService;
import org.spo.cms3.svc.SocketConnector;
import org.spo.ifs3.template.web.Constants;
import org.spo.svc3.trx.pgs.mdl.ActionAssembly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CMSContentPageController {

	@Autowired
	public PageService svc;
	@Autowired
	private Constants constants;
    private static final Logger logger = LoggerFactory
            .getLogger(CMSContentPageController.class);

   
 
    
	private SocketConnector connector=new SocketConnector();
	
	 @RequestMapping(value = "admin/entry", method = RequestMethod.GET)
	 public String home(Locale locale, Model model) {
		 PostContent content1 = new PostContent();
		 content1.setHtmlContent("hello");
		 model.addAttribute("content", content1);
		 List<String> list = svc.readFileCatalog("content",null, null);
		 Collections.sort(list);		 
		 model.addAttribute("files1",list);
		 return "cms1/x_content";
	 }
	 @ResponseBody
	 @RequestMapping(value = "admin/list2/{topic}", method = RequestMethod.GET)
	 public List<String> cmsList2(Locale locale, Model model, @PathVariable String topic) {
//		 PostContent content1 = new PostContent();
//		 content1.setHtmlContent("hello");
//		 model.addAttribute("content", content1);
		 List<String> list = svc.readFileCatalog("content",topic, null);
		 Collections.sort(list);
//		 model.addAttribute("files2",list);
		 return list;
	 }
	 
	 @ResponseBody
	 @RequestMapping(value = "admin/list3/{topic}/{topic2}", method = RequestMethod.GET)
	 public List<String> cmsList3(Locale locale, Model model, @PathVariable String topic, @PathVariable String topic2) {
		 List<String> list = svc.readFileCatalog("content",topic,topic2);
		 Collections.sort(list);		 
		 return list;
	 }
	 
	
	 @ResponseBody
	 @RequestMapping(value = "admin/content/{topic}/{topic2}/{article}", method = RequestMethod.GET)
	 public String cmsList3(Locale locale, Model model, @PathVariable String topic, @PathVariable String topic2, @PathVariable String article) {
		 ActionAssembly assem = new ActionAssembly();
		 assem.setCodes(topic, topic2, article);
		 return svc.readUpPage(assem);
	 }
	
	 @ResponseBody
	 @RequestMapping(value="admin/edit",   params={"fileName"})
	 public String editContent(
		        final PostContent content, final BindingResult bindingResult, final ModelMap model,
		        @RequestParam(value="fileName", required=false) String fileName 
		       ) {
		    if (bindingResult.hasErrors()) {
		        return "seedstartermng";
		    }
		    
		    System.out.println(content.getHtmlContent());
		   // this.seedStarterService.add(seedStarter);
		        logger.info("Searching "+fileName  );
		        QMessage message = new QMessage();
				message.setHandler("fetch");
				//message.setContent(content.getHtmlContent());
				
				String response ="<p>blank reply</p>";
				try {		
					response = svc.readUpPage("posts", fileName);
					String response_meta = svc.readUpPage("posts", fileName+"");
					//response = connector.getResponse(message);
					//TextMessage reply = sender.simpleSend(message.toString()); 
					//response=reply.getText();
					content.setHtmlContent(response);
					content.setMeta(response_meta);
				} catch (Exception e) {			
					e.printStackTrace();
				}
			
			response=response.equals("")?"<p>blank reply</p>":response;
		    model.clear();
		    model.addAttribute("content", message);
		    return response ;
		}
	
	 
	 
	 @ResponseBody
	 @RequestMapping(value="admin/editTemplate",   params={"fileName"})
	 public String editTemplateContent(
		        final PostContent content, final BindingResult bindingResult, final ModelMap model,
		        @RequestParam(value="fileName", required=false) String metaValue) {
		    if (bindingResult.hasErrors()) {
		        return "seedstartermng";
		    }
		    
		    System.out.println(content.getHtmlContent());
		        logger.info("Searching "+metaValue  );
		        QMessage message = new QMessage();
				message.setHandler("fetch");
				//message.setContent(content.getHtmlContent());
				message.setMeta(metaValue);
				String response ="<p>blank reply</p>";
				try {		
					response = svc.readUpPage("templates", metaValue);
					//response = connector.getResponse(message);
					//TextMessage reply = sender.simpleSend(message.toString()); 
					//response=reply.getText();
					content.setHtmlContent(response);
				} catch (Exception e) {			
					e.printStackTrace();
				}
			
			response=response.equals("")?"<p>blank reply</p>":response;
		    //model.clear();
		    //model.addAttribute("content", message);
		    return response ;
		}
	
	 
	@RequestMapping(value="admin/contentSubmit")
	public String processContent(
	        final PostContent content, final BindingResult bindingResult, final ModelMap model) {
	    if (bindingResult.hasErrors()) {
	    	content.setFormErrors(bindingResult.getAllErrors().toString());
	    	model.addAttribute("content", content);
	        return "redirect:entry";
	    }
	    
	    System.out.println(content.getHtmlContent());
	   // this.seedStarterService.add(seedStarter);
	    
	  
	        logger.info("Writing "+content.getMeta()  );
	      
	        
	        QMessage message = new QMessage();
			message.setHandler("write");
			message.setContent(content.getHtmlContent());
			message.setMeta(content.getMeta());
			String response ="";
			try {
				svc.writePage(content.getPath1()+"/"+content.getPath2()+"/"+content.getPath3(),content.getHtmlContent());
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
	    return "cms1/x_content";
	}
	
	@RequestMapping(value="admin/submitContentTemplate")
	public String processContentTemplate(
	        final PostContent content, final BindingResult bindingResult, final ModelMap model) {
	    if (bindingResult.hasErrors()) {
	       // return "y_content";
	    }
	    
	    System.out.println(content.getHtmlContent());
	   // this.seedStarterService.add(seedStarter);
	    
	  
	        logger.info("Writing "+content.getMeta()  );
	       
			try {
				svc.writePage("templates/"+content.getId(), content.getHtmlContent());
				
			} catch (Exception e) {			
				e.printStackTrace();
			}
			String response ="<p>blank reply</p>";
			try {
				content.setHtmlContent("");
				content.setFormErrors("Posted Success");
			} catch (Exception e) {			
				e.printStackTrace();
			}
		
		response=response.equals("")?"<p>blank reply</p>":response;
	    model.clear();
	    model.addAttribute("content", content);
	    return "cms1/y_content";
	}
	
	@ResponseBody
	@RequestMapping(value="admin/backup")
	public String processBackup() {
	    		String response ="<p>blank reply</p>";
			try {		
				response = svc.dumpFilesData();
			
			} catch (Exception e) {			
				e.printStackTrace();
			}
	    return response ;
	}
}
