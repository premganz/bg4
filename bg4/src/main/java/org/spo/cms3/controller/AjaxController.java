//package org.spo.cms3.controller;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Locale;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.spo.cms3.model.QMessage;
//import org.spo.cms3.svc.PageService;
//import org.spo.cms3.svc.SocketConnector;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
//@Controller
//public class AjaxController {
//
//	@Autowired
//	public PageService svc;
//    private static final Logger logger = LoggerFactory
//            .getLogger(AjaxController.class);
//
//   
// 
//    
//	private SocketConnector connector=new SocketConnector();
//	
////	 @RequestMapping(value = "ajax/entry", method = RequestMethod.GET)
////	 public String getDoesList(Locale locale, Model model) {
////		 PostContent content1 = new PostContent();
////		 content1.setHtmlContent("hello");
////		 model.addAttribute("content", content1);
////		 List<String> list = svc.readFileCatalog("does", null);
////		 Collections.sort(list);		 
////		 model.addAttribute("files",list);
////		 return "cms1/x_content";
////	 }
////	 
////	 @RequestMapping(value = "ajax/domainList", method = RequestMethod.GET)
////	 public String home(Locale locale, Model model) {
////		 PostContent content1 = new PostContent();
////		 content1.setHtmlContent("hello");
////		 model.addAttribute("content", content1);
////		 List<String> list = svc.readFileCatalog("does",null);
////		 Collections.sort(list);		 
////		 model.addAttribute("files",list);
////		 return "cms1/x_content";
////	 }
////	 
////	 @RequestMapping(value = "ajax/doesList", method = RequestMethod.GET)
////	 public String getListLevel1(Locale locale, Model model) {
////		 PostContent content1 = new PostContent();
////		 content1.setHtmlContent("hello");
////		 model.addAttribute("content", content1);
////		 
////		 List<String> list = svc.readFileCatalog((String)model.asMap().get("doesCode"), null);
////		 Collections.sort(list);		 
////		 model.addAttribute("files",list);
////		 return "cms1/x_content";
////	 }
////	 
////	 @RequestMapping(value = "ajax/entryTemplate", method = RequestMethod.GET)
////	 public String homeTemplate(Locale locale, Model model) {
////		 PostContent content1 = new PostContent();
////		 content1.setHtmlContent("hello");
////		 model.addAttribute("content", content1);
////		 List<String> list = svc.readFileCatalog("templates", null);
////		 Collections.sort(list);		 
////		 model.addAttribute("files",list);
////		 return "cms1/y_content";
////	 }
//	 
//	 
//	 //MEthod unused
//	 @ResponseBody
//	 @RequestMapping(value="ajax/editOLD",   params={"fileName"})
//	 public String editContent1(
//		        final PostContent content, final BindingResult bindingResult, final ModelMap model,
//		        @RequestParam(value="fileName", required=false) String metaValue) {
//		    if (bindingResult.hasErrors()) {
//		        return "seedstartermng";
//		    }
//		    
//		    System.out.println(content.getHtmlContent());
//		   // this.seedStarterService.add(seedStarter);
//		    
//		  
//		        logger.info("Searching "+metaValue  );
//		      
//		        
//		        QMessage message = new QMessage();
//				message.setHandler("fetch");
//				//message.setContent(content.getHtmlContent());
//				message.setMeta(metaValue);
//				String response ="<p>blank reply</p>";
//				try {		
//					response = connector.getResponse(message);
//					//TextMessage reply = sender.simpleSend(message.toString()); 
//					//response=reply.getText();
//					content.setHtmlContent(response);
//				} catch (Exception e) {			
//					e.printStackTrace();
//				}
//			
//			response=response.equals("")?"<p>blank reply</p>":response;
//		    model.clear();
//		    model.addAttribute("content", message);
//		    return response ;
//		}
//	
//	 @ResponseBody
//	 @RequestMapping(value="ajax/test")
//	 public String editContent(
//		        final PostContent content, final BindingResult bindingResult, final ModelMap model,
//		        @RequestParam(value="fileName", required=false) String fileName 
//		       ) {
//		    if (bindingResult.hasErrors()) {
//		        return "seedstartermng";
//		    }
//		    
//		    System.out.println(content.getHtmlContent());
//		   // this.seedStarterService.add(seedStarter);
//		        logger.info("Searching "+fileName  );
//		        QMessage message = new QMessage();
//				message.setHandler("fetch");
//				//message.setContent(content.getHtmlContent());
//				
//				String response ="<p>blank reply</p>";
//				try {		
//					response = svc.readUpPage("posts", "PRD2");
//					String response_meta = svc.readUpPage("posts", "PRD2");
//					//response = connector.getResponse(message);
//					//TextMessage reply = sender.simpleSend(message.toString()); 
//					//response=reply.getText();
//					content.setHtmlContent(response);
//					content.setMeta(response_meta);
//				} catch (Exception e) {			
//					e.printStackTrace();
//				}
//			
//			response=response.equals("")?"<p>blank reply</p>":response;
//		    model.clear();
//		    model.addAttribute("content", message);
//		    return response ;
//		}
//	
//	 
//	 
//	 @ResponseBody
//	 @RequestMapping(value="ajax/editTemplate",   params={"fileName"})
//	 public String editTemplateContent(
//		        final PostContent content, final BindingResult bindingResult, final ModelMap model,
//		        @RequestParam(value="fileName", required=false) String metaValue) {
//		    if (bindingResult.hasErrors()) {
//		        return "seedstartermng";
//		    }
//		    
//		    System.out.println(content.getHtmlContent());
//		        logger.info("Searching "+metaValue  );
//		        QMessage message = new QMessage();
//				message.setHandler("fetch");
//				//message.setContent(content.getHtmlContent());
//				message.setMeta(metaValue);
//				String response ="<p>blank reply</p>";
//				try {		
//					response = svc.readUpPage("templates", metaValue);
//					//response = connector.getResponse(message);
//					//TextMessage reply = sender.simpleSend(message.toString()); 
//					//response=reply.getText();
//					content.setHtmlContent(response);
//				} catch (Exception e) {			
//					e.printStackTrace();
//				}
//			
//			response=response.equals("")?"<p>blank reply</p>":response;
//		    //model.clear();
//		    //model.addAttribute("content", message);
//		    return response ;
//		}
//	
//	 
//	@RequestMapping(value="ajax/contentSubmit")
//	public String processContent(
//	        final PostContent content, final BindingResult bindingResult, final ModelMap model) {
//	    if (bindingResult.hasErrors()) {
//	    	content.setFormErrors(bindingResult.getAllErrors().toString());
//	    	model.addAttribute("content", content);
//	        return "redirect:entry";
//	    }
//	    
//	    System.out.println(content.getHtmlContent());
//	   // this.seedStarterService.add(seedStarter);
//	    
//	  
//	        logger.info("Writing "+content.getMeta()  );
//	      
//	        
//	        QMessage message = new QMessage();
//			message.setHandler("write");
//			message.setContent(content.getHtmlContent());
//			message.setMeta(content.getMeta());
//			String response ="";
//			try {
//				svc.writePage("posts/"+content.getId(), content.getHtmlContent());
//				svc.writePage("posts/"+content.getId()+"_meta", content.getMeta());
//				//response = connector.getResponse(message);
//				//TextMessage reply = sender.simpleSend(message.toString()); 
//				//response=reply.getText();
//				
//			} catch (Exception e) {			
//				e.printStackTrace();
//			}
//		content.setFormErrors("Success");		
//		content.setHtmlContent("");		
//	    model.clear();
//	    model.addAttribute("content", content);
//	     
//	   // model.clear();
//	    return "cms1/x_content";
//	}
//	
//	@RequestMapping(value="ajax/submitContentTemplate")
//	public String processContentTemplate(
//	        final PostContent content, final BindingResult bindingResult, final ModelMap model) {
//	    if (bindingResult.hasErrors()) {
//	       // return "y_content";
//	    }
//	    
//	    System.out.println(content.getHtmlContent());
//	   // this.seedStarterService.add(seedStarter);
//	    
//	  
//	        logger.info("Writing "+content.getMeta()  );
//	       
//			try {
//				svc.writePage("templates/"+content.getId(), content.getHtmlContent());
//				
//			} catch (Exception e) {			
//				e.printStackTrace();
//			}
//			String response ="<p>blank reply</p>";
//			try {
//				content.setHtmlContent("");
//				content.setFormErrors("Posted Success");
//			} catch (Exception e) {			
//				e.printStackTrace();
//			}
//		
//		response=response.equals("")?"<p>blank reply</p>":response;
//	    model.clear();
//	    model.addAttribute("content", content);
//	    return "cms1/y_content";
//	}
//	
//	@ResponseBody
//	@RequestMapping(value="ajax/backup")
//	public String processBackup() {
//	    		String response ="<p>blank reply</p>";
//			try {		
//				response = svc.dumpFilesData();
//			
//			} catch (Exception e) {			
//				e.printStackTrace();
//			}
//	    return response ;
//	}
//	
//	
//	
//	
//	
//}
