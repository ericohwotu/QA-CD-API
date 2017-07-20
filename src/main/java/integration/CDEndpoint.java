package integration;

import business.CDService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;

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
        System.out.println("this is me printing some stuff");
        return service.getAllCDs();
    }

    @Path("/json/{id}")
    @PUT
    @Produces({"application/json"})
    public void updateCD(@PathParam("id") int id){
        service.updateCD(id);
    }

    @Path("/json/{id}")
    @DELETE
    @Produces({"application/json"})
    public void deleteCD(@PathParam("id") int id){
        service.deleteCD(id);
    }

    @Path("/json")
    @POST
    @Produces({"application/json"})
    public String addCD(String movie){
        return service.addCD(movie);
    }
}
