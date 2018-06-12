package org.spo.svc3.trx.pgs.t01.handler;

import org.spo.ifs3.dsl.controller.AbstractHandler;
import org.spo.ifs3.dsl.controller.DSLConstants.EventType;
import org.spo.ifs3.dsl.controller.NavEvent;
import org.spo.svc3.trx.pgs.t01.task.T0101;
import org.spo.svc3.trx.pgs.t01.task.T0102;
import org.spo.svc3.trx.pgs.t01.task.T0103;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class T01Handler extends AbstractHandler{

   
   @Autowired
   T0101 t0101;
   
   @Autowired
   T0102 t0102;
   
   @Autowired
   T0103 t0103;
   
   public static final NavEvent EV_INIT_01 =  NavEvent.create(EventType.REFRESHPAGE);
   public static final NavEvent EV_INIT_02 =  NavEvent.create(EventType.REFRESHPAGE, "c01/C0102");
   public static final NavEvent EV_INIT_03 =  NavEvent.create(EventType.REFRESHPAGE);
   
   public static final NavEvent EV_SWITCH_TO_DTL =  NavEvent.create(EventType.TASKSET, "02");   
   public static final NavEvent EV_SWITCH_TO_INST =  NavEvent.create(EventType.TASKSET, "03");
   
   public static final NavEvent EV_PVS =  NavEvent.create(EventType.TASKSET, "01");
   public static final NavEvent EV_PVS_01 =  NavEvent.create(EventType.TASKSET, "01");
   
   
   public static final NavEvent EV_REFRESH_CONTENT =  NavEvent.create(EventType.TASKSET, "01");
   public static final NavEvent EV_REFRESH_NEW_SUB_LAND =  NavEvent.create(EventType.TRXSWITCH, "C01");
   public static final NavEvent EV_SWITCH_TO_OVV =  NavEvent.create(EventType.TRXSWITCH, "M01");
  
  
   @Override
   public void configureChannel() {
			taskChannel.put("01",t0101);
			taskChannel.put("02",t0102);
			taskChannel.put("03",t0103);
   }
   
   
  
    
	
	
	
	
	
	
}
