package org.spo.svc3.trx.pgs.k02.handler;

import org.spo.ifs3.dsl.controller.AbstractHandler;
import org.spo.ifs3.dsl.controller.DSLConstants.EventType;
import org.spo.ifs3.dsl.controller.NavEvent;
import org.spo.svc3.trx.pgs.k02.task.K0201;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class K02Handler extends AbstractHandler{

   
   @Autowired
   K0201 k0101;
   
  public static final NavEvent EV_INIT_01 =  NavEvent.create(EventType.REFRESHPAGE);
  public static final NavEvent EV_REFRESH_PAGE =  NavEvent.create(EventType.TASKSET, "01");
  
   @Override
   public void configureChannel() {
			taskChannel.put("01",k0101);
			
   }
   
   
  
    
	
	
	
	
	
	
}
