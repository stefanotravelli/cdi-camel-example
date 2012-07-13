package org.fusesource.example.cdi.camel.deltaspike.spi;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.component.cdi.CdiCamelContext;
import org.apache.camel.impl.CamelPostProcessorHelper;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.*;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;

public class CamelAnnotationsExtension extends CamelPostProcessorHelper implements Extension {

    Class<?> typeRetrieved;
    EndpointInject inject;
    CamelContext ctx = new CdiCamelContext();

    void discoverAnnotations(@Observes ProcessAnnotatedType<?> annotatedType) {

        Set<AnnotatedField<?>> fields = (Set<AnnotatedField<?>>) annotatedType.getAnnotatedType().getFields();

        for(AnnotatedField af : fields) {
            Annotation a = af.getAnnotation(EndpointInject.class);
            if (a != null) {
               inject = (EndpointInject)a;
               System.out.println("Endpoint inject ref : " + inject.ref());
               System.out.println("Endpoint inject uri : " + inject.uri());
               System.out.println("Endpoint inject context : " + inject.context());

                Field f = af.getJavaMember();
                typeRetrieved = f.getDeclaringClass();

            }

        }

    }

    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {

        System.out.println("Finished the scanning process");

        CamelPostProcessorHelper cph = new CamelPostProcessorHelper(ctx);

        Class<?> type = typeRetrieved;

        String injectionPointName = type.getName();
        String uri = inject.uri();
        String endpointRef = inject.ref();

        cph.getInjectionValue(Endpoint.class, uri, endpointRef, injectionPointName, null, null);


    }

}
