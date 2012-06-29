package org.fusesource.example.cdi.camel.deltaspike;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: charlesmoulliard
 * Date: 16/02/12
 */
public class BootStrap {

    final private static Logger logger = LoggerFactory.getLogger(BootStrap.class);

    public static void main(String[] args) throws Exception {

        CdiContainer cc = CdiContainerLoader.getCdiContainer();
        cc.boot();
        cc.getContextControl().startContexts();
        logger.info(">> CDI container started");

        BootCamel bc = new BootCamel();
        bc.init();

        Thread.sleep(2000);

        cc.getContextControl().stopContexts();
        cc.shutdown();
    }

}
