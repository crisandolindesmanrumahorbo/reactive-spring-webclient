## Reactive WebClient on Spring Boot

https://www.baeldung.com/spring-5-webclient

Generic Java Reference
https://www.geeksforgeeks.org/generics-in-java/


Unit Testing Reference
https://howtodoinjava.com/spring-webflux/webfluxtest-with-webtestclient/

Component scan reference
https://www.baeldung.com/spring-nosuchbeandefinitionexception

Factory pattern
https://www.geeksforgeeks.org/factory-method-design-pattern-in-java/

If we run use Tomcat Servlet Container, the service implement Tomcat NIO Connector which support Non Blocking / Async.
https://dzone.com/articles/understanding-tomcat-nio

By default, webflux run with Netty Servlet Container, Netty implement Java NIO too.
https://www.baeldung.com/spring-webflux-concurrency

Java NIO: https://jenkov.com/tutorials/java-nio/index.html 

#### Netty

##### How to run using Netty Server?
Just exclude the default tomcat server
```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
```

If we run use Netty Server, and trying to hit endpoint sync (which use block()), will display this error. 
```
    java.lang.IllegalStateException: block()/blockFirst()/blockLast() are blocking, which is not supported in thread reactor-http-nio-3
```
#### Netty only support nonblock, if want to run nonblock and block api use tomcat or undertow.

#### JMeter
How to Run?
```
    cd apache-jmeter-5.4.3/bin
    sh jmeter.sh
```
