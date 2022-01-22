package org.spo.svc3.trx.pgs.k03.handler;

import org.spo.ifs3.dsl.controller.AbstractHandler;
import org.spo.ifs3.dsl.controller.DSLConstants.EventType;
import org.spo.ifs3.dsl.controller.NavEvent;
import org.spo.svc3.trx.pgs.k03.task.K0301;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class K03Handler extends AbstractHandler{

   
   @Autowired
   K0301 k0301;
   
  public static final NavEvent EV_INIT_01 =  NavEvent.create(EventType.REFRESHPAGE);
  
   @Override
   public void configureChannel() {
			taskChannel.put("01",k0301);
			
   }
   
   
  
    
	
	
	
	
	
	
}
