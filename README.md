# Camel CDI

## Simple Example using Camel in a CDI environment

With Apache Camel 2.10, it will be possible to deploy Camel Integration project
in a CDI container (JSR299, JSR330).


The project can be started locally using the command

    maven package

as we have configured in the pom.xml project the glassfish embedded maven plugin.

When Glassfish is started, the log EIP of Camel will produce the following output every 10s.

    INFO: >> Response : >> Hello Bean Injected user.
    INFO: >> Response : >> Hello Bean Injected user.

Alternatively, you can deploy the war file produced in any J2EE 6 servers like Glassfish, JBoss AS 7,
OpenEJB, Apache TomEE or Apache KarafEE

The camel CDI riders !