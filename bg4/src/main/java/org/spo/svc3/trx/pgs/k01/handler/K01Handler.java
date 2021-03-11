package org.spo.svc3.trx.pgs.k01.handler;

import org.spo.ifs3.dsl.controller.AbstractHandler;
import org.spo.ifs3.dsl.controller.DSLConstants.EventType;
import org.spo.ifs3.dsl.controller.NavEvent;
import org.spo.svc3.trx.pgs.k01.task.K0101;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class K01Handler extends AbstractHandler{

   
   @Autowired
   K0101 k0101;
//   @Autowired
//   K0102 k0102;
  
   
  public static final NavEvent EV_INIT_01 =  NavEvent.create(EventType.REFRESHPAGE);
  public static final NavEvent EV_INIT_02 =  NavEvent.create(EventType.REFRESHPAGE);
  public static final NavEvent EV_SWITCH_SUB_LAND =  NavEvent.create(EventType.TASKSET, "02");
  
   @Override
   public void configureChannel() {
			taskChannel.put("01",k0101);
//			taskChannel.put("02",k0102);
			
   }
   
   
  
    
	
	
	
	
	
	
}
