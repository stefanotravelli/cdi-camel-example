# Camel CDI example deplyed in DeltaSpike Weld

## Execute project

mvn clean package exec:java

## Result

Here is the output that deltaspike weld will produce on the console

    16 [org.jboss.weld.environment.se.StartMain.main()] INFO org.jboss.weld.Version - WELD-000900 1.1.5 (Final)
    198 [org.jboss.weld.environment.se.StartMain.main()] INFO org.jboss.weld.Bootstrap - WELD-000101 Transactional services not available. Injection of @Inject UserTransaction not available. Transactional observers will be invoked synchronously.
    Jun 29, 2012 2:29:54 PM org.apache.deltaspike.core.util.ClassDeactivationUtils cacheResult
    INFO: class: org.apache.deltaspike.core.impl.exception.control.extension.ExceptionControlExtension activated=true
    Jun 29, 2012 2:29:54 PM org.apache.deltaspike.core.util.ClassDeactivationUtils cacheResult
    INFO: class: org.apache.deltaspike.core.impl.message.MessageBundleExtension activated=true
    Jun 29, 2012 2:29:55 PM org.apache.deltaspike.core.util.ClassDeactivationUtils cacheResult
    INFO: class: org.apache.deltaspike.core.impl.exclude.extension.ExcludeExtension activated=true
    Jun 29, 2012 2:29:55 PM org.apache.deltaspike.core.util.ClassDeactivationUtils cacheResult
    INFO: class: org.apache.deltaspike.core.impl.exclude.GlobalAlternative activated=true
    Jun 29, 2012 2:29:55 PM org.apache.deltaspike.core.util.ClassDeactivationUtils cacheResult
    INFO: class: org.apache.deltaspike.core.impl.exclude.CustomProjectStageBeanFilter activated=true
    Jun 29, 2012 2:29:55 PM org.apache.deltaspike.core.util.ProjectStageProducer initProjectStage
    INFO: Computed the following DeltaSpike ProjectStage: Production
    1978 [org.jboss.weld.environment.se.StartMain.main()] WARN org.jboss.interceptor.util.InterceptionTypeRegistry - Class 'javax.ejb.PostActivate' not found, interception based on it is not enabled
    1978 [org.jboss.weld.environment.se.StartMain.main()] WARN org.jboss.interceptor.util.InterceptionTypeRegistry - Class 'javax.ejb.PrePassivate' not found, interception based on it is not enabled
    2690 [org.jboss.weld.environment.se.StartMain.main()] INFO org.fusesource.example.cdi.camel.deltaspike.weld.BootStrap - >> Create CamelContext and register Camel Route.
    3085 [org.jboss.weld.environment.se.StartMain.main()] INFO org.apache.camel.component.cdi.CdiCamelContext - Apache Camel 2.10-SNAPSHOT (CamelContext: camel-1) is starting
    3448 [org.jboss.weld.environment.se.StartMain.main()] INFO org.apache.camel.management.ManagementStrategyFactory - JMX enabled.
    3448 [org.jboss.weld.environment.se.StartMain.main()] INFO org.apache.camel.management.DefaultManagementLifecycleStrategy - StatisticsLevel at All so enabling load performance statistics
    4426 [org.jboss.weld.environment.se.StartMain.main()] INFO org.apache.camel.impl.converter.DefaultTypeConverter - Loaded 170 type converters
    5606 [org.jboss.weld.environment.se.StartMain.main()] INFO org.apache.camel.component.cdi.CdiCamelContext - Route: route1 started and consuming from: Endpoint[timer://simple?fixedRate=true&period=10s]
    5606 [org.jboss.weld.environment.se.StartMain.main()] INFO org.apache.camel.component.cdi.CdiCamelContext - Total 1 routes, of which 1 is started.
    5633 [org.jboss.weld.environment.se.StartMain.main()] INFO org.apache.camel.component.cdi.CdiCamelContext - Apache Camel 2.10-SNAPSHOT (CamelContext: camel-1) started in 2.522 seconds
    5713 [Camel (camel-1) thread #1 - timer://simple] INFO route1 - >> Response : >> Hello Bean Injected user.