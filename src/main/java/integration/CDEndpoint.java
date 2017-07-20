package integration;

import business.CDService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Administrator on 20/07/2017.
 */
@Path("/cd")
public class CDEndpoint {

    @Inject
    private CDService service;

    @Path("/json")
    @GET
    @Produces({"application/json"})
    public String getAllCDs(){
        return service.getAllCDs();
    }

    @Path("/json")
    @PUT
    @Produces({"application/json"})
    public String updateCD(){
        return "CD updated";
    }
}
