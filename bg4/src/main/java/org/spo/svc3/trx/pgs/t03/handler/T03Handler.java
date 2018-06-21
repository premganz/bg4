package org.spo.svc3.trx.pgs.t03.handler;

import org.spo.ifs3.dsl.controller.AbstractHandler;
import org.spo.ifs3.dsl.controller.DSLConstants.EventType;
import org.spo.ifs3.dsl.controller.NavEvent;
import org.spo.svc3.trx.pgs.t03.task.T0301;
import org.spo.svc3.trx.pgs.t03.task.T0302;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class T03Handler extends AbstractHandler{

   
   @Autowired
   T0301 t0301;
   @Autowired
   T0302 t0302;
   
   
  //unused
   public static final NavEvent EV_NAV_MENU =  NavEvent.create(EventType.TRXSWITCH, "T01/Wel_msg");
   
   
   public static final NavEvent EV_INIT_01 =  NavEvent.create(EventType.REFRESHPAGE);
   public static final NavEvent EV_INIT_02 =  NavEvent.create(EventType.REFRESHPAGE);
   
   public static final NavEvent EV_REG =  NavEvent.create(EventType.TASKSET, "02");
   public static final NavEvent EV_PVS_MENU =  NavEvent.create(EventType.TASKSET, "01");
   
   @Override
   public void configureChannel() {
			taskChannel.put("01",t0301);
			taskChannel.put("02",t0302);
   }
   
   
  
    
	
	
	
	
	
	
}
