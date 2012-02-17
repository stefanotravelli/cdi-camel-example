package com.fusesource.cdi.camel.simple;

import org.apache.camel.Produce;
import org.apache.camel.component.cdi.CdiBeanRegistry;
import org.apache.camel.component.timer.TimerEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;


/**
 * User: charlesmoulliard
 * Date: 16/02/12
 */
@Singleton
@Startup
public class BootStrap {

    Logger logger = LoggerFactory.getLogger(BootStrap.class);

    DefaultCamelContext camelCtx;
    String timerUri = "timer://simple?fixedRate=true&period=10s";

    @PostConstruct
    public void init() throws Exception {
            logger.info(">> Create CamelContext and register Camel Route.");
            camelCtx = createContext();
            camelCtx.setRegistry(new CdiBeanRegistry());
            camelCtx.start();
            logger.info(">> CamelContext created and camel route started.");
    }

    @PreDestroy
    public void stop() throws Exception {
       camelCtx.stop();
    }

    @Named("timer:simple")
    @Produce public TimerEndpoint createMyTimerEndpoint() {
        return (TimerEndpoint) camelCtx.getEndpoint(timerUri);
    }

    // @Named(value = "simpleContext")
    // @Produce
    public DefaultCamelContext createContext() throws Exception {
        DefaultCamelContext context = new DefaultCamelContext();
        context.addRoutes(new SimpleCamelRoute());
        return context;
    }


}
