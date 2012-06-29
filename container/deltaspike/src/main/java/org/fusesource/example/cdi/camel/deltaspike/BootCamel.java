package org.fusesource.example.cdi.camel.deltaspike;

import org.apache.camel.component.cdi.CdiCamelContext;
import org.fusesource.example.cdi.camel.SimpleCamelRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static org.apache.deltaspike.core.api.provider.BeanProvider.injectFields;

public class BootCamel {

    final private static Logger logger = LoggerFactory.getLogger(BootCamel.class);

    @Inject
    protected CdiCamelContext camelCtx;

    @Inject
    protected SimpleCamelRoute simpleRoute;

    public  void init() throws Exception {

        injectFields(this);

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
