package org.spo.svc3.trx.pgs.t02.handler;

import org.spo.ifs3.dsl.controller.AbstractHandler;
import org.spo.ifs3.dsl.controller.DSLConstants.EventType;
import org.spo.svc3.trx.pgs.t02.task.T0201;
import org.spo.ifs3.dsl.controller.NavEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class T02Handler extends AbstractHandler{

   
   @Autowired
   T0201 t0201;
   
  
   
  public static final NavEvent EV_INIT_01 =  NavEvent.create(EventType.REFRESHPAGE);
  public static final NavEvent EV_INIT_02 =  NavEvent.create(EventType.REFRESHPAGE);//loads m01/M0102
  public static final NavEvent EV_INIT_03 =  NavEvent.create(EventType.REFRESHPAGE);//loads m01/M0102
  public static final NavEvent EV_SWITCH_TO_BLOG_LANDING =  NavEvent.create(EventType.TASKSET, "02");
  public static final NavEvent EV_SWITCH_TO_CONTENT =  NavEvent.create(EventType.TRXSWITCH, "C01");
  public static final NavEvent EV_SWITCH_SUB_LAND =  NavEvent.create(EventType.TRXSWITCH, "C01");
  
   @Override
   public void configureChannel() {
			taskChannel.put("01",t0201);
			
   }
   
   
  
    
	
	
	
	
	
	
}
