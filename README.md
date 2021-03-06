# Trace-Starter

[![CircleCI](https://circleci.com/gh/loanfulfilment/loan-gateway.svg?style=svg)](https://circleci.com/gh/loanfulfilment/trace-starter)

This is the library used by loan fulfilment to do open tracing using Jaeger.

## Learning

#### How to create a library with beans

1. Entry of the bean in the `spring.factories` like below

`org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.swapnilsankla.tracestarter.TraceConfiguration`

2. Make sure you don't add `spring-boot` plugin in the library project

#### How to publish to maven local?

1. Add `maven-publish` plugin
2. Add following to build.gradle

        `publishing {
               publications {
                   mavenJava(MavenPublication) {
                       from components.java
                   }
               }
           }`
            
3. Run `gradle publishToMavenLocal`  
4. The library will be published under `~/.m2/repository`
