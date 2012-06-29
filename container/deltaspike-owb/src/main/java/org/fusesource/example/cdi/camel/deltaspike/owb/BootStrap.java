package org.fusesource.example.cdi.camel.deltaspike.owb;

import org.apache.camel.component.cdi.CdiCamelContext;
import org.fusesource.example.cdi.camel.SimpleCamelRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;


/**
 * User: charlesmoulliard
 * Date: 16/02/12
 */
public class BootStrap {

    final private static Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Inject
    protected static CdiCamelContext camelCtx;

    @Inject
    protected static SimpleCamelRoute simpleRoute;


    public static void main(String[] args) throws Exception {

        CdiContainer cdiContainer = new CdiContainer();
        cdiContainer.start();

        init();

        cdiContainer.stop();
    }

    public static void init() throws Exception {
        //You can use CDI here - since you can't inject a bean in this class directly use the BeanManagerProvider or the BeanProvider
        logger.info(">> Create CamelContext and register Camel Route.");

        // Define Timer URI
        simpleRoute.setTimerUri("timer://simple?fixedRate=true&period=10s");

        // Add Camel Route
        camelCtx.addRoutes(simpleRoute);

        // Start Camel Context
        camelCtx.start();

        logger.info(">> CamelContext created and camel route started.");
    }


}
