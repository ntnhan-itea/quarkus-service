package com.nhan.nguyenthanh.johnnyservice.boundary;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@ApplicationScoped
@Path("init")
public class InitLoadResource {

    private String test;

    @GET
    public String ping() {
        return "pong";
    }
}
