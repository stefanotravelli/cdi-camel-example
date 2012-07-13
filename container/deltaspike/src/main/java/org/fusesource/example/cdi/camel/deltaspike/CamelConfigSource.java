package org.fusesource.example.cdi.camel.deltaspike;

import org.apache.deltaspike.core.api.config.*;

public class CamelConfigSource implements PropertyConfigSource {

    @Override
    public String getPropertyFileName() {
        return "META-INF/camel.properties";
    };

}
