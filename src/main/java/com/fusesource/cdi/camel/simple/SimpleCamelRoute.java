package com.fusesource.cdi.camel.simple;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: charlesmoulliard
 * Date: 16/02/12.
 */
public class SimpleCamelRoute extends RouteBuilder {

        @Inject @Named("timer:simple")
        Endpoint timerEndpoint;

        @Override
        public void configure() throws Exception {

            from(timerEndpoint)
               .setBody()
                 .simple("Bean Injected")
            // Lookup for bean injected by CDIcontainer
            // The HellowWorld class is annotated using @Named
            .bean(HelloWorld.class, "sayHello")
            // Using Camel lookup mechanism
            // .bean(HelloWorld.class,"sayHello")
            .log(">> Response : ${body}");

        }
    }