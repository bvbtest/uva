package nl.uva.api;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Component
@Path("/api/v1/")
public class Endpoint {

    @GET
    @Path("/awesome/{id}")
    public Object getAwesome(@PathParam("id") String id) {
        if (id.equals("2020")) {
            return "Awesome 2020";
        } else if (id.equals("2021")) {
            return "Awesome 2021";
        } else return "Bullshit";
    }

}
