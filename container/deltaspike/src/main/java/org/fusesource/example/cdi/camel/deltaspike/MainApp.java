package org.fusesource.example.cdi.camel.deltaspike;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * User: charlesmoulliard
 * Date: 16/02/12
 */
public class MainApp {

    final private static Logger logger = LoggerFactory.getLogger(MainApp.class);

    @Inject
    // Example 1
    // We will use a Simple Camel Route
    // where the timer Endpoint is defined using Camel Annotation (EndpointInject)
    @CamelRoute(Type.SIMPLE)

    // Example 2
    // We use a Simple Camel Route
    // where the timer Endpoint is defined using CDI Extension with ConfigSource
    // @CamelRoute(Type.SIMPLE_WITH_PROPERTY)
    public CamelConfigure camelConfigure;

    public static void main(String[] args) throws Exception {

        CdiContainer cc = CdiContainerLoader.getCdiContainer();
        cc.boot();
        cc.getContextControl().startContexts();
        logger.info(">> CDI container started");

        MainApp mainApp = new MainApp();
        BeanProvider.injectFields(mainApp);

        // Call init method to start camel route
        mainApp.camelConfigure.addRouteAndStart();

        Thread.sleep(2000);

        cc.getContextControl().stopContexts();
        cc.shutdown();
    }

}
