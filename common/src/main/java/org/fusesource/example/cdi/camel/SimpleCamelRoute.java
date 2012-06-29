package org.fusesource.example.cdi.camel;

import org.apache.camel.builder.RouteBuilder;

/**
 * User: charlesmoulliard
 * Date: 16/02/12.
 */
public class SimpleCamelRoute extends RouteBuilder {

    private String timerUri;

    @Override
    public void configure() throws Exception {

        from(timerUri)
            .setBody()
                .simple("Bean Injected")

                    // Lookup for bean injected by CDIcontainer
                    // The HellowWorld class is annotated using @Named
            .beanRef("helloWorld", "sayHello")

                    // Using Camel lookup mechanism
                    // .bean(HelloWorld.class,"sayHello")

            .log(">> Response : ${body}");

    }

    public void setTimerUri(String timerUri) {
        this.timerUri = timerUri;
    }

}