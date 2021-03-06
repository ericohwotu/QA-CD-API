package integration;

import business.APIKeyService;
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

    @Inject
    private APIKeyService key;

    private static String ERROR_JSON = "{\"Error\":\"API Key not recognised\"}";

    //=============================================   GET   ==========================================//
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

    //=============================================   Update   ==========================================//
    @Path("/json/key={api}&id={id}")
    @PUT
    @Produces({"application/json"})
    public String updateCD(@PathParam("api") String api, @PathParam("id") long id, String cd) {
        if (key.checkAPIKey(api))
            return service.updateCD(api,id, cd);
        else
            return ERROR_JSON;
    }

    //=============================================  DELETE   ==========================================//
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

    //=============================================   ADD CD   ==========================================//
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

    //=============================================   ADD API   ==========================================//
    @Path("/key/user={user}")
    @POST
    @Produces({"application/json"})
    public String getKey(@PathParam("user") String user) {
        return key.genAPIKey(user);
    }

    //=============================================   NO KEY ERROR   ======================================//
    @Path("/json")
    @POST
    @Produces({"application/json"})
    public String postNoKeyError() {
        return "{\"Error\":\"API Key required\"}";
    }

    @Path("/json")
    @PUT
    @Produces({"application/json"})
    public String putNoKeyError() {
        return "{\"Error\":\"API Key required\"}";
    }

    @Path("/json")
    @DELETE
    @Produces({"application/json"})
    public String deleteNoKeyError() {
        return "{\"Error\":\"API Key required\"}";
    }

    //=============================================   Query Key   ==========================================//
//    @Path("/key/check={key}")
//    @GET
//    @Produces({"application/json"})
//    public String isKey(@PathParam("key") String api) {
//        return key.isApiCorrect(api);
//    }


}

