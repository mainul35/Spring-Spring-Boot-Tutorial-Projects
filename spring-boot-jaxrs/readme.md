## Required Dependency

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jersey</artifactId>
</dependency>
```

## Jersey Configuration

```
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {
        packages("com.mainul35.springbootjaxrs.resource");
    }
}
```

We need to introduce this class as a Jersey Context type class by using `@Import` annotation over Spring Boot main class.

```
@SpringBootApplication
@Import(value = {JerseyConfig.class})
public class SpringBootJaxrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJaxrsApplication.class, args);
	}

}
```

## Controller code example

```
@Component
@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getById() {
        return userService.getAll();
    }

    @Path("/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getById(@PathParam("username") final String username) {
        return userService.getByUsername(username);
    }
}

```

* `@Path` is a Jersey annotation
* `@Inject` is a JakartaEE (Formerly known as Java EE) annotation
* `@GET / @POST / @PUT / @DELETE` annotations are Jersey annotations
* `@Produces / @Consumes` are Jersey annotations
