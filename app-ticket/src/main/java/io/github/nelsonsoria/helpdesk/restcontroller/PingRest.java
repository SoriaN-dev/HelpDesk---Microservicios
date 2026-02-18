package io.github.nelsonsoria.helpdesk.restcontroller;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/ping")
public class PingRest {
    @GET
    public String ping (){
        return "pong";
    }
}
