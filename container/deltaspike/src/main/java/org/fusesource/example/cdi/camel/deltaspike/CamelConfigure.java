package org.fusesource.example.cdi.camel.deltaspike;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.cdi.CdiCamelContext;
import org.apache.deltaspike.core.api.config.annotation.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.inject.Inject;
import javax.inject.Qualifier;

import static org.apache.deltaspike.core.api.provider.BeanProvider.injectFields;

public class CamelConfigure {

    final private static Logger logger = LoggerFactory.getLogger(CamelConfigure.class);

    @Inject
    protected CdiCamelContext camelCtx;

    @Inject
    protected org.fusesource.example.cdi.camel.SimpleCamelRoute simpleRoute;

    @Inject
    @ConfigProperty(name = "timerEndpoint")
    protected String timerEndpointDefinedWithCDIProperty;

    // protected String timerEndpointDefinedUsingCamelAnnotation = "timer://simple?fixedRate=true&period=5s";
    @Inject
    @EndpointInject(uri = "timer://simple?fixedRate=true&period=5s")
    protected String timerEndpointDefinedUsingCamelAnnotation;


    public CamelConfigure() {
        injectFields(this);
    }

    @Produces
    @CamelRoute(Type.SIMPLE_WITH_PROPERTY)
    public CamelConfigure getSimpleRouteUsingCDIPropertyForEndpoint() {
        CamelConfigure camelConfigure = new CamelConfigure();
        camelConfigure.useCdiPropertyToDefineTimerEndpoint();
        return camelConfigure;
    }

    @Produces
    @CamelRoute(Type.SIMPLE)
    public CamelConfigure getSimpleRoute() {
        CamelConfigure camelConfigure = new CamelConfigure();
        camelConfigure.useCamelEndpointInjectToDefineTimerEndpoint();
        return camelConfigure;
    }

    public void useCdiPropertyToDefineTimerEndpoint() {
        simpleRoute.setTimerUri(timerEndpointDefinedWithCDIProperty);
    }

    public void useCamelEndpointInjectToDefineTimerEndpoint() {
        simpleRoute.setTimerUri(timerEndpointDefinedUsingCamelAnnotation);
    }

    public void addRouteAndStart() throws Exception {

        //You can use CDI here - since you can't inject a bean in this class directly use the BeanManagerProvider or the BeanProvider
        logger.info(">> Create CamelContext and register Camel Route.");

        // Add Camel Route
        camelCtx.addRoutes(simpleRoute);

        // Start Camel Context
        camelCtx.start();

        logger.info(">> CamelContext created and camel route started.");
    }

}