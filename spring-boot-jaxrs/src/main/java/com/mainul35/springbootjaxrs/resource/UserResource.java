package com.mainul35.springbootjaxrs.resource;

import com.mainul35.springbootjaxrs.model.User;
import com.mainul35.springbootjaxrs.service.UserService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
