# Camel CDI

## Simple Example using Camel in a CDI environment

With Apache Camel 2.10, it will be possible to deploy Camel Integration project
in a CDI container (JSR299, JSR330). The following example shows how we can bootstrap
an Apache Camel Container in a class which is a javax.ejb.Singleton. In combinaition
with javax.ejb.Startup annotation, this class is strated by the CDI-EJB container at startup.
When the @PreConstruct annotation is called, then we inject a CdiCamelContext objet, register
a SimpleCamelRoute using @Inject annotation and starts the Camel Routes.

When you look to the Camel code, you can see that we do a lookup to find another bean "helloWorlkd"
which has been injected. This is possible because the CdiCamelContext registers a Camel Registry containing
a reference to the CDI BeanManager.

    @Override
    public void configure() throws Exception {

        from(timerUri)
            .setBody()
                .simple("Bean Injected")

            // Lookup for bean injected by CDIcontainer
            // The HellowWorld class is annotated using @Named
            .beanRef("helloWorld", "sayHello")

            .log(">> Response : ${body}");

    }


The project can be started locally using the command

    maven package

as we have configured in the pom.xml project the glassfish embedded maven plugin.

 when Glassfish is started, the log EIP of Camel will produces the following output every 10s.

Alternatively, you can deploy the war file produced in any J2EE 6 servers like Glassfish, JBoss AS 7,
OpenEJB, Apache TomEE or Apache KarafEE

The camel CDI riders !