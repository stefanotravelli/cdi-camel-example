package com.fusesource.cdi.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;


@Singleton
@Startup
public class BootStrap2 {

    Logger logger = LoggerFactory.getLogger(BootStrap2.class);

    @Inject
    HelloCdiWorld helloCdiWorld;

    @PostConstruct
    public void init() throws Exception {

         // Call Method
         logger.info(helloCdiWorld.sayHellotoCdi("Charles"));

    }

    @PreDestroy
    public void stop() throws Exception {
        logger.info(">> Destroyed");
    }



}
