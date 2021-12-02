package nl.uva.api;

import nl.uva.data.MyInputModel;
import nl.uva.domain.InputCalculator;
import nl.uva.domain.InputCalculatorImpl;
import org.springframework.stereotype.Component;

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
    public Long getAwesome(MyInputModel model) {
        InputCalculator inputCalculator = new InputCalculatorImpl();
        return inputCalculator.getCalculation(model);
    }

}
