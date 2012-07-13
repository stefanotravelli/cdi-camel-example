package org.fusesource.example.cdi.camel;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

/**
 * User: charlesmoulliard
 * Date: 16/02/12.
 */
public class SimpleCamelEndpointRoute extends RouteBuilder {

    private Endpoint timerUri;

    @Override
    public void configure() throws Exception {

        from(timerUri)
            // Set Body with text "Bean Injected"
            .setBody().simple("Bean Injected")

            // Lookup for bean injected by CDIcontainer
            .beanRef("helloWorld", "sayHello")
            // Display response received in log when calling HelloWorld
            .log(">> Response : ${body}");

    }

    public void setTimerUri(Endpoint timerUri) {
        this.timerUri = timerUri;
    }

}