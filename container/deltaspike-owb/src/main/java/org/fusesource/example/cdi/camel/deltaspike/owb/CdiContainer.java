package org.fusesource.example.cdi.camel.deltaspike.owb;

import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;

/**
 * Helper class to start and stop the OpenWebBeansContainer
 */
public class CdiContainer {
    private static ContainerLifecycle lifecycle = null;

    public static void start() {
        lifecycle = WebBeansContext.currentInstance().getService(ContainerLifecycle.class);
        lifecycle.startApplication(null);
    }

    public static void stop() {
        lifecycle.stopApplication(null);
    }

}