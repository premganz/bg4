package org.spo.ifs.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.ifs3.config.RootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


/**
 * Application entry point.
 *
 * This class implements and bootstraps the Spring application context. It also
 * listens for application events to ensure the SpringMVC application context is
 * successfully loaded.
 */
public class Main {

    /**
     * Flag that will be set to true when the web application context
     * (SpringMVC) is refreshed.
     */
    static boolean webApplicationContextInitialized = false;

    public static void main(String[] args) throws Exception {

        final Logger logger = LoggerFactory.getLogger("main");

        try {
            AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

            /*
             * One problem with SpringMVC is it creates its own application
             * context, and so it can end up failing but our application will
             * keep running.
             * 
             * To detect the case where the SpringMVC's web application context
             * fails we'll listen for ContextRefreshEvents and set a flag when
             * we see one.
             */
//            applicationContext
//                    .addApplicationListener(new ApplicationListener<ContextRefreshedEvent>() {
//                       
//                        public void onApplicationEvent(
//                                ContextRefreshedEvent event) {
//                            ApplicationContext ctx = event.getApplicationContext();
//                            if (ctx instanceof AnnotationConfigWebApplicationContext) {
//                                webApplicationContextInitialized = true;
//                            }
//                        }
//                    });

            applicationContext.registerShutdownHook();
            applicationContext.register(RootConfiguration.class);
            applicationContext.register(org.spo.ifs3.config.RootConfiguration.class);
            applicationContext.refresh();
         
            /*In case you want to start a  second container and jetty instance on a seperate port
             * All you need to do is to uncommment the part below, create a new folder called ifs3 and copy the entire ifs2 into it, 
             * Make sure you also create a new svc3 as a replica of svc2 and then you map the whole thing svc3 to ifs3 and 
             * you are ready to go. 
             *  
             */
            
            
            
            
            
            AnnotationConfigApplicationContext applicationContext1 = new AnnotationConfigApplicationContext();
//            applicationContext1.addApplicationListener(new ApplicationListener<ContextRefreshedEvent>() {
//               
//                public void onApplicationEvent(
//                        ContextRefreshedEvent event) {
//                    ApplicationContext ctx = event.getApplicationContext();
//                    if (ctx instanceof AnnotationConfigWebApplicationContext) {
//                        webApplicationContextInitialized = true;
//                    }
//                }
//            });

    applicationContext1.registerShutdownHook();
    
            applicationContext1.register(org.spo.ifs4.config.RootConfiguration.class);
            applicationContext1.refresh();
           
            logger.info("Running.");
        } catch (Exception e) {
            logger.error("Error starting application", e);
            System.exit(1);
        }
    }
}
