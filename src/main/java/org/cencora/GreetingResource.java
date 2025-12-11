package org.cencora;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello";
    }

    @GET
    @Path("/quarkus")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloQuarkus() {
        return "Hello Quarkus!";
    }

    @GET
    @Path("/date")
    @Produces(MediaType.TEXT_PLAIN)
    public String date() {
        return LocalDate.now().toString();
    }

    public String sayHello() {
        return "hello";
    }

}
