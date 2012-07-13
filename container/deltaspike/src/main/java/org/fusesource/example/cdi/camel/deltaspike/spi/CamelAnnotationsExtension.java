package org.fusesource.example.cdi.camel.deltaspike.spi;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.impl.CamelPostProcessorHelper;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;

public class CamelAnnotationsExtension extends CamelPostProcessorHelper implements Extension {

    public void discoverAnnotations(@Observes ProcessAnnotatedType<?> annotatedType) {

        Set<AnnotatedField<?>> fields = (Set<AnnotatedField<?>>) annotatedType.getAnnotatedType().getFields();

        for(AnnotatedField af : fields) {
            Annotation a = af.getAnnotation(EndpointInject.class);
            if (a != null) {
               EndpointInject inject = (EndpointInject)a;
               System.out.println("Endpoint inject ref : " + inject.ref());
               System.out.println("Endpoint inject uri : " + inject.uri());
               System.out.println("Endpoint inject context : " + inject.context());

                Field f = af.getJavaMember();

                String injectionPointName = f.getName();
                String uri = inject.uri();
                String endpointRef = inject.ref();

                getInjectionValue(EndpointInject.class, uri, endpointRef, injectionPointName, null, null);

            }

        }

    }

}
