package com.fusesource.cdi.camel.simple;

import org.apache.camel.Body;

import javax.inject.Named;

/**
 * User: charlesmoulliard
 * Date: 17/02/12
 */
@Named
public class HelloWorld {

    public String sayHello(@Body String message) {
        return ">> Hello " + message + " user.";
    }
}
