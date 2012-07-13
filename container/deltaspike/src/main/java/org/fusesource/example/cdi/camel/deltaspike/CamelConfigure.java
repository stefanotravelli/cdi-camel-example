package org.fusesource.example.cdi.camel.deltaspike;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.component.cdi.CdiCamelContext;
import org.apache.deltaspike.core.api.config.annotation.ConfigProperty;
import org.fusesource.example.cdi.camel.SimpleCamelEndpointRoute;
import org.fusesource.example.cdi.camel.SimpleCamelRoute;
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
    protected SimpleCamelRoute simpleRoute;

    @Inject
    protected SimpleCamelEndpointRoute simpleEndpointRoute;

    @Inject
    @ConfigProperty(name = "timerEndpoint")
    protected String timerEndpointDefinedWithCDIProperty;

    // protected String timerEndpointDefinedUsingCamelAnnotation = "timer://simple?fixedRate=true&period=5s";
    @EndpointInject(uri = "timer://simple?fixedRate=true&period=5s")
    protected Endpoint timerEndpoint;

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
        simpleEndpointRoute.setTimerUri(timerEndpoint);
    }

    public void addRouteAndStart() throws Exception {

        //You can use CDI here - since you can't inject a bean in this class directly use the BeanManagerProvider or the BeanProvider
        logger.info(">> Create CamelContext and register Camel Route.");

        // Add Camel Route
        // camelCtx.addRoutes(simpleRoute);
        camelCtx.addRoutes(simpleEndpointRoute);

        // Start Camel Context
        camelCtx.start();

        logger.info(">> CamelContext created and camel route started.");
    }

}
