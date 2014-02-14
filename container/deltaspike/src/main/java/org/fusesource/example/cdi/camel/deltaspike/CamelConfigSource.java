package org.fusesource.example.cdi.camel.deltaspike;

import org.apache.deltaspike.core.api.config.*;

public class CamelConfigSource implements PropertyFileConfig {

    @Override
    public String getPropertyFileName() {
        return "META-INF/camel.properties";
    };

}
