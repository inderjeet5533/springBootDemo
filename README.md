# springBootDemo
Spring Boot Bean Validation.

to call API's add header key: "authorization" and value "test_auth"

H2 DB implementation with Spring Data JPA.

H2 DB console accessible at --> http://localhost:8080/h2-console

Login H2 console with credentials present in yml file.

Junit Demo for Controller and service.

negative controller testing also added

# Interceptor

You can use the Interceptor in Spring Boot to perform operations 
under the following situations.

1. Before sending the request to the controller
2. Before sending the response to the client

check GlobalInterceptor and InterceptorConfig class
no extra dependency required.

to test Interceptor's preHandle remove header key: "authorization"