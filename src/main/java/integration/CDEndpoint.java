package integration;

import business.APIKeyService;
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

    @Inject
    private APIKeyService key;

    @Path("/json")
    @GET
    @Produces({"application/json"})
    public String getAllCDs(){
        return service.getAllCDs();
    }

    @Path("/json/{id : \\d+}")
    @GET
    @Produces({"application/json"})
    public String getCD(@PathParam("id") long id){
        return service.getCD(id);
    }

    @Path("/json/name={name : [A-Za-z][A-Za-z0-9]*}")
    @GET
    @Produces({"application/json"})
    public String getCDByName(@PathParam("name") String name){
        return service.getCDByName(name);
    }

    @Path("/json/artist={artist : [A-Za-z][A-Za-z0-9]*}")
    @GET
    @Produces({"application/json"})
    public String getCDByArtist(@PathParam("artist") String artist){
        return service.getCDByArtist(artist);
    }

    @Path("/json/genre={genre : [A-Za-z]*}")
    @GET
    @Produces({"application/json"})
    public String getCDByGenre(@PathParam("genre") String genre){
        return service.getCDByGenre(genre);
    }

    @Path("/json/year={year : [0-9]*}")
    @GET
    @Produces({"application/json"})
    public String getCDByYear(@PathParam("year") String year){
        return service.getCDByYear(year);
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
    @DELETE
    @Produces({"application/json"})
    public String deleteAll(){
        return service.deleteAll();
    }

    @Path("/json")
    @POST
    @Produces({"application/json"})
    public String addCD(String movie){
        return service.addCD(movie);
    }

    @Path("/json/multi")
    @POST
    @Produces({"application/json"})
    public String addCDs(String movie){
        return service.addCDs(movie);
    }

    @Path("/key/user={user}")
    @POST
    @Produces({"application/json"})
    public String getKey(@PathParam("user") String user){
        return key.genAPIKey(user);
    }
    @Path("/key/check={key}")
    @GET
    @Produces({"application/json"})
    public String isKey(@PathParam("key") String api){
        return key.isApiCorrect(api);
    }
}
