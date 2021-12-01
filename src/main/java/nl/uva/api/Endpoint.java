package nl.uva.api;

import nl.uva.data.MyInputModel;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

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

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/awesome/")
    public MyInputModel getAwesome(@Valid MyInputModel model) {
        return model;
    }

}
