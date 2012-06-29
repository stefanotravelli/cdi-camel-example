package org.fusesource.example.cdi.camel.glassfish;

import org.apache.camel.component.cdi.CdiCamelContext;
import org.fusesource.example.cdi.camel.SimpleCamelRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;


/**
 * User: charlesmoulliard
 * Date: 16/02/12
 */
@Singleton
@Startup
public class BootStrap {

    Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Inject
    CdiCamelContext camelCtx;

    @Inject
    SimpleCamelRoute simpleRoute;

    @PostConstruct
    public void init() throws Exception {
            logger.info(">> Create CamelContext and register Camel Route.");

            // Define Timer URI
            simpleRoute.setTimerUri("timer://simple?fixedRate=true&period=10s");

            // Add Camel Route
            camelCtx.addRoutes(simpleRoute);

            // Start Camel Context
            camelCtx.start();

            logger.info(">> CamelContext created and camel route started.");
    }

    @PreDestroy
    public void stop() throws Exception {
       camelCtx.stop();
    }


}
