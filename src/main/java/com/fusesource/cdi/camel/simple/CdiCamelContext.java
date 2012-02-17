package com.fusesource.cdi.camel.simple;

import org.apache.camel.Endpoint;
import org.apache.camel.component.cdi.CdiBeanRegistry;
import org.apache.camel.component.timer.TimerEndpoint;
import org.apache.camel.impl.DefaultCamelContext;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import static org.apache.camel.util.ObjectHelper.notNull;

/**
 * User: charlesmoulliard
 * Date: 17/02/12
 */
public class CdiCamelContext {

    DefaultCamelContext context;

    String timerUri = "timer://simple?fixedRate=true&period=10s";

    @Named(value = "simpleContext") @Produces
    public DefaultCamelContext createContext() throws Exception {
        this.context = new DefaultCamelContext();
        this.context.setRegistry(new CdiBeanRegistry());
        return context;
    }


    @Named(value = "timer-simple") @Produces
    public TimerEndpoint createMyTimerEndpoint() {
        notNull(context,"context");
        Endpoint myEndpoint = this.context.getEndpoint(timerUri);
        return (TimerEndpoint)myEndpoint ;
    }

}
