package org.fusesource.example.cdi.camel.deltaspike.owb;

import org.apache.camel.component.cdi.CdiCamelContext;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.fusesource.example.cdi.camel.SimpleCamelRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.util.Set;

import static org.apache.deltaspike.core.api.provider.BeanProvider.injectFields;

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

    /*        BeanManager bm = cc.getBeanManager();
 logger.info(">> BeanManager retrieved");

 Set<Bean<?>> beans = bm.getBeans(SimpleCamelRoute.class);
 Bean<?> bean = bm.resolve(beans);
 logger.info(">> Bean SimpleCamelRoute resolved");

 simpleRoute = (SimpleCamelRoute) bm.getReference(bean, SimpleCamelRoute.class, bm.createCreationalContext(bean));
 logger.info(">> SimpleCamelRoute Bean created");

 beans = bm.getBeans(CdiCamelContext.class);
 bean = bm.resolve(beans);
 logger.info(">> Bean CdiCamelContext resolved");

 camelCtx = (CdiCamelContext) bm.getReference(bean, CdiCamelContext.class, bm.createCreationalContext(bean));
 logger.info(">> CamelContext Bean created");*/

}
