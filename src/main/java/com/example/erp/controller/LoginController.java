package com.example.erp.controller;

import com.example.erp.services.LoginService;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
@Path("form")
public class LoginController {
    @GET
    @Path("login/{username}/{password}")
    //@Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response registerCourse(@PathParam("username") String name,
                                   @PathParam("password") String pass) throws URISyntaxException {
        System.out.println("login page");
        System.out.println(name);
        LoginService lgs = new LoginService();
        String result = lgs.login(name, pass);
        System.out.println(result);
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("login_user/{username}/{password}")
    //@Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response loginuser(@PathParam("username") String name,
                                   @PathParam("password") String pass) throws URISyntaxException {
        System.out.println("login user");
        LoginService lgs = new LoginService();
        String result = lgs.login1(name, pass);
        System.out.println(result);
        return Response.status(200).entity(result).build();
    }
}
