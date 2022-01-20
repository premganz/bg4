package org.spo.cms3.controller;

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
import org.spo.svc3.trx.pgs.utils.CmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CMSContentPageController {

	@Autowired
	public PageService svc;
	@Autowired
	private Constants constants;
	private static final Logger logger = LoggerFactory
			.getLogger(CMSContentPageController.class);

	@Autowired
	public CmsUtils cmsUtils;


	private SocketConnector connector=new SocketConnector();

	@RequestMapping(value = "admin/entry", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		PostContent content1 = new PostContent();
		content1.setHtmlContent("hello");
		model.addAttribute("content", content1);
		List<String> list = svc.readFileCatalog("content",null, null,null);
		Collections.sort(list);		 
		model.addAttribute("files1",list);
		return "cms1/x_content";
	}
	
	//LISTS 
	
	@ResponseBody
	@RequestMapping(value = "admin/list2/{topic}", method = RequestMethod.GET)
	public List<String> cmsList2(Locale locale, Model model, @PathVariable String topic) {
		List<String> list = svc.readFileCatalog("content",topic, null,null);
		Collections.sort(list);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "admin/list3/{topic}/{topic2}", method = RequestMethod.GET)
	public List<String> cmsList3(Locale locale, Model model, @PathVariable String topic, @PathVariable String topic2) {
		List<String> list = svc.readFileCatalog("content",topic,topic2,null);
		Collections.sort(list);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "admin/list4/{topic}/{topic2}/{topic3}", method = RequestMethod.GET)
	public List<String> cmsList4(Locale locale, Model model, @PathVariable String topic, @PathVariable String topic2, @PathVariable String topic3) {
		List<String> list = svc.readFileCatalog("content",topic,topic2, topic3);
		Collections.sort(list);
		return list;
	}
	
	//CONTENT

	@ResponseBody
	@RequestMapping(value = "admin/content/{major}/{minor}/{action}/{article}", method = RequestMethod.GET)
	public String fetchContent(Locale locale, Model model, @PathVariable String major, @PathVariable String minor, 
			@PathVariable String action, @PathVariable String article) {
		//TO fix the problem, where article extension alone does not arrive, due to the use of . dot character
		if(!article.isEmpty() && !article.endsWith(".txt")) article=article+".txt";
		if(minor.equals("index.txt")) {
			minor="";action="";article="";
		}else if("index.txt".contentEquals(action)) {
			article="";
			action="";
		}
		ActionAssembly assem = new ActionAssembly();
		assem.setCodes(major, minor, action, article);
		
//		assem.setExtension(".txt");
		return svc.readUpPage(assem);
	}
	
	@ResponseBody
	@RequestMapping(value = "admin/contentmeta/{major}/{minor}/{action}/{article}", method = RequestMethod.GET)
	public String fetchContentMeta(Locale locale, Model model, @PathVariable String major, @PathVariable String minor, 
			@PathVariable String action, @PathVariable String article) {
		//TO fix the problem, where article extension alone does not arrive, due to the use of . dot character
		if(!article.isEmpty() && !article.endsWith(".txt")) article=article+".txt";
		if(minor.equals("index.txt")) {
			minor="";action="";article="";
		}else if("index.txt".contentEquals(action)) {
			article="";
			action="";
		}
		ActionAssembly assem = new ActionAssembly();
		assem.setCodes(major, minor, action, article);
		
//		assem.setExtension(".txt");
		return svc.readUpPageMeta(assem);
	}

	@ResponseBody
	@RequestMapping(value = "admin/contentStaging/{major}/{minor}/{action}/{article}", method = RequestMethod.GET)
	public String fetchContentStaging(Locale locale, Model model,  @PathVariable String major, @PathVariable String minor,@PathVariable String action, @PathVariable String article) {
		ActionAssembly assem = new ActionAssembly();
		assem.setCodes(major, minor, action, article);
		return svc.readUpPageStaging(assem);
	}
	
	@ResponseBody
	@RequestMapping(value = "admin/contentStagingMeta/{topic}/{topic2}/{article}", method = RequestMethod.GET)
	public String fetchContentStagingMeta(Locale locale, Model model, @PathVariable String major, @PathVariable String minor,@PathVariable String action, @PathVariable String article) {
		ActionAssembly assem = new ActionAssembly();
		assem.setCodes(major, minor, action, article);
		return svc.readUpPageStagingMeta(assem);
	}
	@ResponseBody
	@RequestMapping(value = "admin/content/fetchTemplate", method = RequestMethod.GET)
	public String fetchTemplate(Locale locale, Model model) {
		ActionAssembly assem = new ActionAssembly();
		assem.setCodes("meta", "meta-schema", "","Schema_cms.xml");		 
		return cmsUtils.formatXml(svc.readUpPage(assem));
	}

	@ResponseBody
	@RequestMapping(value = "admin/content/createFile/{major}/{minor}/{action}/{article}", method = RequestMethod.GET)
	public String createFile(Locale locale, Model model, @PathVariable String major, @PathVariable String minor,@PathVariable String action, @PathVariable String article) {
		ActionAssembly assem = new ActionAssembly();
		if(!article.isEmpty() && !article.endsWith(".txt")) article=article+".txt";
		assem.setCodes(major, minor, action, article);
		svc.createFile(assem);
		return "done";
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
		logger.info("Writing "+content.getMeta()  );
		QMessage message = new QMessage();
		message.setHandler("write");
		message.setContent(content.getHtmlContent());
		message.setMeta(content.getMeta());
		String response ="";
		try {
			String pathToWrite = getPath(content);
			svc.writePage(pathToWrite,content.getHtmlContent(),
					content.getMeta(),content.getPublishReady());
			

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
			svc.writePage("templates/"+content.getId(), content.getHtmlContent(),content.getMeta(),
					content.getPublishReady());

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
}
