package org.fusesource.example.cdi.camel.deltaspike.weld;

import org.apache.camel.component.cdi.CdiCamelContext;
import org.fusesource.example.cdi.camel.SimpleCamelRoute;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.inject.Inject;


/**
 * User: charlesmoulliard
 * Date: 16/02/12
 */
public class BootStrap {

    final private static Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Inject
    protected CdiCamelContext camelCtx;

    @Inject
    protected SimpleCamelRoute simpleRoute;


    public void sayHelloCamelRoute(@Observes ContainerInitialized event) throws Exception {

        //You can use CDI here - since you can't inject a bean in this class directly use the BeanManagerProvider or the BeanProvider
        logger.info(">> Create CamelContext and register Camel Route.");

        // Define Timer URI
        simpleRoute.setTimerUri("timer://simple?fixedRate=true&period=10s");

        // Add Camel Route
        camelCtx.addRoutes(simpleRoute);

        // Start Camel Context
        camelCtx.start();

        Thread.sleep(2000);

        logger.info(">> CamelContext created and camel route started.");

    }


}
