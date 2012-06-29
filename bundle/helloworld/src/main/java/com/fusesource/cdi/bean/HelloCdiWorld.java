package com.fusesource.cdi.bean;

import javax.inject.Named;

@Named
public class HelloCdiWorld {

    public String sayHellotoCdi(String message) {
        return ">> Hello " + message + " user.";
    }
}
