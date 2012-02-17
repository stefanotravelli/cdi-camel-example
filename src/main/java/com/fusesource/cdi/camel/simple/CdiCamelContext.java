package com.fusesource.cdi.camel.simple;

import org.apache.camel.Endpoint;
import org.apache.camel.component.cdi.CdiBeanRegistry;
import org.apache.camel.impl.DefaultCamelContext;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * User: charlesmoulliard
 * Date: 17/02/12
 */
public class CdiCamelContext {

    public CdiCamelContext() {
        this.context = new DefaultCamelContext();
    }

    DefaultCamelContext context;

    @Named("simpleContext") @Produces
    public DefaultCamelContext createContext() throws Exception {
        context.setRegistry(new CdiBeanRegistry());
        return context;
    }

    //@Named("endpoint")
    @Produces
    public Endpoint createEndpoint(@Named("uri") String uri) {
        return context.getEndpoint(uri);
    }

}
