package com.fusesource.cdi.camel.simple;

import org.apache.camel.component.cdi.CdiCamelContext;
import org.jboss.weld.environment.osgi.api.annotation.OSGiService;
import org.jboss.weld.environment.osgi.api.annotation.Publish;
import org.jboss.weld.environment.osgi.api.events.BundleContainerEvents;
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
// @Singleton
// @Startup
public class BootStrap {

    Logger logger = LoggerFactory.getLogger(BootStrap.class);

    @Inject @OSGiService
    CdiCamelContext camelCtx;

    @Inject
    SimpleCamelRoute simpleRoute;

    public void onStartup(@Observes BundleContainerEvents.BundleContainerInitialized event) throws Exception {
        logger.info(">> Create CamelContext and register Camel Route.");

        // Define Timer URI
        simpleRoute.setTimerUri("timer://simple?fixedRate=true&period=10s");

        // Add Camel Route
        camelCtx.addRoutes(simpleRoute);

        // Start Camel Context
        camelCtx.start();

        logger.info(">> CamelContext created and camel route started.");
    }

    public void onShutdown(@Observes BundleContainerEvents.BundleContainerShutdown event) throws Exception {
        camelCtx.stop();
    }

    /**
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
     */



}
