package org.spo.ifs3.dsl.controller;

import java.util.LinkedHashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.ifs3.dsl.controller.TrxInfo.Scope;
import org.spo.ifs3.template.web.Constants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DelegatingController{

	protected static final Logger logger = LoggerFactory.getLogger(AbstractHandler.class);
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	Constants constants;
 

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "redirect:"+constants.getLandingPage();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(Locale locale, Model model) {

		return "redirect:"+constants.getLandingPage();
	}

	@RequestMapping(value="/trx/{trxId}/{dataId}", method = RequestMethod.GET)
	public String trxSwitch(     final ModelMap modelMap,HttpServletRequest request,
			@PathVariable String trxId, @PathVariable String dataId, HttpSession session,
			@RequestParam(value="event", required=false) String pageEvent) {
		TrxInfo info = null;
		StateInfo state = null;
		if(session.isNew()){
			info = new TrxInfo(session, modelMap,request);
			session.setAttribute("info",info);
			state = new StateInfo(DSLConstants.EventType.REFRESHPAGE,trxId,"","",dataId);
			info.put(new ScopeVar(Scope.TRX, "stateInfo"),state);
		}else{
			info = (TrxInfo)session.getAttribute("info");
			if(info==null) {
				//means that the session has expired
				info = new TrxInfo(session, modelMap,request);
				session.setAttribute("info",info);
			}
			info.refreshModelMap(modelMap);
			state=info.getState();
			
		}

		if(pageEvent!=null && state==null){				throw new DSLException();}
		AbstractHandler handler = resolveHanlder(trxId);
		return handler.handle1(pageEvent,dataId,state,info,request);
	}
	
	@RequestMapping(value="/trx/{trxId}/FORM", method = RequestMethod.POST)
	 public String submitContact(final Forms content, final Forms form, 
			 final BindingResult bindingResult, HttpSession session,final ModelMap modelMap,HttpServletRequest request,
			 @PathVariable String trxId)			
				 {		
		TrxInfo info = (TrxInfo)session.getAttribute("info");
		info.updateModelMap(modelMap);
		StateInfo state=info.getState();
		 if (bindingResult.hasErrors()) {
			 return "seedstartermng";
		 }
		 info = (TrxInfo)session.getAttribute("info");
		 info.put(AbstractToolkit.SV_FORM,form.getForm());
		 AbstractHandler handler = resolveHanlder(trxId);
		 return handler.handle2(state,info,request);
	 }



	//	@RequestMapping(value="/trx1/{trxId}/{dataId}", method = RequestMethod.GET)
	//	public String trxEvent(     final ModelMap info,
	//			@PathVariable String trxId, @PathVariable String dataId, HttpSession session,  
	//			@RequestParam(value="event", required=true) String pageEvent) {		
	//		StateInfo state = (StateInfo)session.getAttribute("state");
	//		AbstractHandler handler = resolveHanlder(trxId);
	//		return handler.handle(pageEvent,state,info,session);		
	//	}
	//	

	//
	//	
	//	@RequestMapping(value="/trx/{trxId}/event/{eventId}", method = RequestMethod.GET)
	//	public String processEvent(     final ModelMap info,
	//			@PathVariable String trxId, @PathVariable String eventId, HttpSession session) {		
	//		StateInfo state = (StateInfo)session.getAttribute("state");
	//		AbstractHandler handler = resolveHanlder(trxId);
	//		return handler.handle(eventId,state,info,session);		
	//	}
	//	@RequestMapping(value="/trx/{trxId}/view/{pageId}", method = RequestMethod.GET)
	//	public String refreshView(  final ModelMap info,
	//			@PathVariable String pageId, @PathVariable String trxId) {		
	//		StateInfo state = new StateInfo(DSLConstants.EventType.REFRESHPAGE,trxId,"","",dataId);
	//		session.setAttribute("state", state);
	//		AbstractHandler handler = resolveHanlder(trxId);
	//		return handler.handle(dataId,state,info,session);		
	//	}
	//
	//
	//	@RequestMapping(value="/trx/ajaxcall/{trxId}/{eventId}/(dataId}", method = RequestMethod.GET)
	//	@ResponseBody
	//	public String ajaxCall(    final BindingResult bindingResult, final ModelMap info,
	//			@PathVariable String eventId, @PathVariable String trxId) {
	//		StateInfo event = new StateInfo(EventType.AJAXCALL, eventId);
	//		AbstractHandler concernedHandler = (AbstractHandler)appContext.getBean(trxId+"Handler");
	//		return concernedHandler.handle(event, info);
	//		
	//	}
	//	
	//	@RequestMapping(value="taskset/{trxId}/{eventId}/(dataId}", method = RequestMethod.GET)
	//	public String taskSet(    final BindingResult bindingResult, final ModelMap info,
	//			@PathVariable String eventId, @PathVariable String trxId) {
	//		StateInfo event = new StateInfo(EventType.TASKSET, eventId);
	//		AbstractHandler concernedHandler = (AbstractHandler)appContext.getBean(trxId+"Handler");
	//		return concernedHandler.handle(event, info);
	//		
	//	}
	//	
	//	@RequestMapping(value="event/(dataId}", method = RequestMethod.GET)
	//	public String processEvent( final ModelMap info,
	//			 @PathVariable String dataId
	//			,HttpSession session) {
	//		String taskId= (String)session.getAttribute("taskId");
	//		String trxId= (String)session.getAttribute("trxId");
	//		StateInfo event = new StateInfo(EventType.PROCESSEVENT, taskId+"/"+dataId);
	//		AbstractHandler concernedHandler = (AbstractHandler)appContext.getBean(trxId+"Handler");
	//		return concernedHandler.handle(event, info);
	//		
	//	}


	private AbstractHandler resolveHanlder(String trxId){
		AbstractHandler concernedHandler=null;
		try {
			concernedHandler = (AbstractHandler)appContext.getBean(Class.forName("org.spo.svc3.trx.pgs."+trxId.toLowerCase()+ ".handler."+trxId+"Handler"));
		} catch (BeansException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return concernedHandler;
	}
}
