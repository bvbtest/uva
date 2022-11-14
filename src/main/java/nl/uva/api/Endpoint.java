package nl.uva.api;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@Component
@Path("/api/v1/")
public class Endpoint {

    @GET
    @Path("/awesome/")
    public Object getAwesome() {
        return "Awesome";
    }
}
