# Trace-Starter

This is the library used by loan fulfilment to do open tracing using Jaeger.

## Learning

#### How to create a library with beans

. Entry of the bean in the `spring.factories` like below

`org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.swapnilsankla.tracestarter.TraceConfiguration`

. Make sure you don't add `spring-boot` plugin in the library project

#### How to publish to maven local?

. Add `maven-publish` plugin
. Add following to build.gradle

        `publishing {
               publications {
                   mavenJava(MavenPublication) {
                       from components.java
                   }
               }
           }`
            
. Run `gradle publishToMavenLocal`  
. The library will be published under `~/.m2/repository`
