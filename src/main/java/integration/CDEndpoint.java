package integration;

import business.CDService;

import javax.inject.Inject;
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

    @Path("/json/{id : \\d+}")
    @GET
    @Produces({"application/json"})
    public String getCD(@PathParam("id") long id){
        return service.getCD(id);
    }

    @Path("/json/{name : [A-Za-z][A-Za-z0-9]*}")
    @GET
    @Produces({"application/json"})
    public String getCD(@PathParam("name") String name){
        return service.getCD(name);
    }

    @Path("/json/{id}")
    @PUT
    @Produces({"application/json"})
    public String updateCD(@PathParam("id") long id, String cd){
        return service.updateCD(id, cd);
    }

    @Path("/json/{id}")
    @DELETE
    @Produces({"application/json"})
    public String deleteCD(@PathParam("id") long id){
        return service.deleteCD(id);
    }

    @Path("/json")
    @POST
    @Produces({"application/json"})
    public String addCD(String movie){
        return service.addCD(movie);
    }
}
