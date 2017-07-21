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

    private static String ERROR_JSON = "{\"Error\":\"API Key not recognised\"}";


    @Path("/json")
    @GET
    @Produces({"application/json"})
    public String getAllCDs() {
        return service.getAllCDs();
    }

    @Path("/json/{id : \\d+}")
    @GET
    @Produces({"application/json"})
    public String getCD(@PathParam("id") long id) {
        return service.getCD(id);
    }

    @Path("/json/name={name}")
    @GET
    @Produces({"application/json"})
    public String getCDByName(@PathParam("name") String name) {
        return service.getCDByName(name);
    }

    @Path("/json/artist={artist}")
    @GET
    @Produces({"application/json"})
    public String getCDByArtist(@PathParam("artist") String artist) {
        return service.getCDByArtist(artist);
    }

    @Path("/json/genre={genre}")
    @GET
    @Produces({"application/json"})
    public String getCDByGenre(@PathParam("genre") String genre) {
        return service.getCDByGenre(genre);
    }

    @Path("/json/year={year : [0-9]*}")
    @GET
    @Produces({"application/json"})
    public String getCDByYear(@PathParam("year") String year) {
        return service.getCDByYear(year);
    }

    @Path("/json/key={api}&id={id}")
    @PUT
    @Produces({"application/json"})
    public String updateCD(@PathParam("api") String api, @PathParam("id") long id, String cd) {
        if (key.checkAPIKey(api))
            return service.updateCD(api,id, cd);
        else
            return ERROR_JSON;
    }

    @Path("/json/key={api}&id={id}")
    @DELETE
    @Produces({"application/json"})
    public String deleteCD(@PathParam("api") String api, @PathParam("id") long id) {
        if (key.checkAPIKey(api))
            return service.deleteCD(api, id);
        else
            return ERROR_JSON;
    }

    @Path("/json/key={api}")
    @DELETE
    @Produces({"application/json"})
    public String deleteAll(@PathParam("api") String api) {
        if (key.checkAPIKey(api))
            return service.deleteAll();
        else
            return ERROR_JSON;
    }

    @Path("/json/key={api}")
    @POST
    @Produces({"application/json"})
    public String addCD(@PathParam("api") String api, String cd) {
        if (key.checkAPIKey(api))
            return service.addCD(api, cd);
        else
            return ERROR_JSON;
    }

    @Path("/json/key={api}&multi=true")
    @POST
    @Produces({"application/json"})
    public String addCDs(@PathParam("api") String api, String cd) {
        if (key.checkAPIKey(api))
            return service.addCDs(api, cd);
        else
            return ERROR_JSON;
    }

    @Path("/key/user={user}")
    @POST
    @Produces({"application/json"})
    public String getKey(@PathParam("user") String user) {
        return key.genAPIKey(user);
    }

    @Path("/json")
    @POST
    @Produces({"application/json"})
    public String noKeyError() {
        return "{\"Error\":\"API Key required\"}";
    }

    @Path("/key/check={key}")
    @GET
    @Produces({"application/json"})
    public String isKey(@PathParam("key") String api) {
        return key.isApiCorrect(api);
    }


}

