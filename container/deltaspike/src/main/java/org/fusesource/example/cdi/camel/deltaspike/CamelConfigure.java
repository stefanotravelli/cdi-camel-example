package org.fusesource.example.cdi.camel.deltaspike;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.component.cdi.CdiCamelContext;
import org.apache.deltaspike.core.api.config.annotation.ConfigProperty;
import org.fusesource.example.cdi.camel.SimpleCamelEndpointRoute;
import org.fusesource.example.cdi.camel.SimpleCamelRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import static org.apache.deltaspike.core.api.provider.BeanProvider.injectFields;

@ApplicationScoped
public class CamelConfigure {

    final private static Logger logger = LoggerFactory.getLogger(CamelConfigure.class);

    @Inject
    protected CdiCamelContext ctx;

    @Inject
    protected SimpleCamelRoute simpleRoute;

    @Inject
    protected SimpleCamelEndpointRoute simpleEndpointRoute;

    @Inject
    @ConfigProperty(name = "timerEndpoint")
    protected String timerUriDefinedWithCDIProperty;

    @EndpointInject(uri = "timer://simple?fixedRate=true&period=5s")
    protected Endpoint timerEndpoint;

    @Produces
    @CamelRoute(Type.CDI_PROPERTY)
    public void useRouteWithCDIPropertyForUri() throws Exception {
        simpleRoute.setTimerUri(timerUriDefinedWithCDIProperty);
    }

    @Produces
    @CamelRoute(Type.ENDPOINT)
    public void useRouteWithEndpointInject() throws Exception {
        simpleEndpointRoute.setTimerEndpoint(timerEndpoint);
    }

    public CamelConfigure() {
        injectFields(this);
    }

    public void configRoute(Type type) {
        switch (type) {
            case CDI_PROPERTY:
                simpleRoute.setTimerUri(timerUriDefinedWithCDIProperty); break;
            case ENDPOINT:
                simpleEndpointRoute.setTimerEndpoint(timerEndpoint); break;
        }
    }

    public void startRoute(Type type) throws Exception {
        switch (type) {
            case CDI_PROPERTY:
                ctx.addRoutes(simpleRoute); break;
            case ENDPOINT:
                ctx.addRoutes(simpleEndpointRoute); break;
        }
        ctx.start();
    }

}
