package com.fusesource.cdi.camel.simple;

import org.apache.camel.builder.RouteBuilder;

/**
 * User: charlesmoulliard
 * Date: 16/02/12.
 */
public class SimpleCamelRoute extends RouteBuilder {

        // @Inject @Named("timer-simple")
        // Endpoint timerEndpoint;

        @Override
        public void configure() throws Exception {

            from("timer-simple")
               .setBody()
                 .simple("Bean Injected")

            // Lookup for bean injected by CDIcontainer
            // The HellowWorld class is annotated using @Named
            .beanRef("helloWorld", "sayHello")

            // Using Camel lookup mechanism
            // .bean(HelloWorld.class,"sayHello")

            .log(">> Response : ${body}");

        }
    }