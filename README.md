### Spring Controller Demo

A simple [Spring Boot](http://projects.spring.io/spring-boot/)
demo application with a `@RestController` that returns
different types of responses based on the version requested:

- `FooResponseV1` for version 1
- `FooResponseV2` for version 2, or unspecified

(both implement the `FooResponse` interface)

Additionally, an `ErrorReponse` type is thrown if a bad version is requested.

Check out `src/main/java/org/xerotrope/FooController.java` and it's tests:
`src/test/java/org/xerotrope/FooControllerTest.java`.
