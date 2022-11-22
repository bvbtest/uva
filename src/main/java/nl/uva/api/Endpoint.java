package nl.uva.api;

import org.springframework.stereotype.Component;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Component
@Path("/api/v1")
public class Endpoint {

    @Path("/awesome")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAwesome() {
        return "Awesome";
    }

    @Path("/awesome/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAwesome(@PathParam("id") String id) {
        if ("1".equals(id)) {
            return "Awesome";
        }
        else if ("2".equals(id)) {
            return "Also Awesome";
        }
        else {
            return "Not Awesome";
        }
    }

}
