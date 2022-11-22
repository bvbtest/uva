package nl.uva.api;

import nl.uva.data.MyInputModel;
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
    public MyInputModel getAwesome() {
        MyInputModel myCustomInputModel = new MyInputModel();
        myCustomInputModel.setJaartal(2022);
        myCustomInputModel.setBsn("111111110");
        myCustomInputModel.setDiabetes(false);
        myCustomInputModel.setVoldoetAanKeurmerk(true);
        myCustomInputModel.setGramSuiker(100);
        myCustomInputModel.setGewichtProductInGram(1000);
        return myCustomInputModel;
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
