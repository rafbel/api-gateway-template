# API Gateway Template

This is an easy go to template I created so I can use whenever I need to start up a new gateway. It is built using Spring Boot, 
Spring Security and Netflix Zuul. It is registered as an Eureka Client, so it can discover microservices in your VPC by asking your 
Eureka Server (I also have a template for this one! Check it out at https://github.com/rafbel/eureka-server-template).

Additionaly, it uses Spring Security to check for endpoint necessary authorization. Since we don't want the gateway to handle
authentication, I have created an Authenticator microservice (template will be available on GitHub soon!) that uses Oath2 to handle
this issue.

I used Gradle for this project, but it is pretty easy to change to Maven if you prefer it.


