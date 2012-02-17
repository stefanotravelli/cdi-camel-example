package com.fusesource.cdi.camel.simple;

import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * User: charlesmoulliard
 * Date: 16/02/12.
 */
public class SimpleCamelRoute extends RouteBuilder {

        @Named("uri") @Produces
        String timerUri = "timer://simple?fixedRate=true&period=10s";

        @Override
        public void configure() throws Exception {

            from("endpoint")
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