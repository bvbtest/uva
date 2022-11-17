package nl.uva.api;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
