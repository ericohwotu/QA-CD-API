package business;

import persistance.CD;
import persistance.ExposedCD;
import util.JSONUtil;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Stateless
@Default
public class CDServiceImpl implements CDService {

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    @Inject
    private JSONUtil util;

    @Inject
    private APIKeyService apiService;

    private static String ERROR_JSON = "{\"Error\":\"You do not have permission to perform this action\"}";
    private static String NO_ARTWORK = "http://d3u67r7pp2lrq5.cloudfront.net/product_photos/30619921/pompeya-real-vinyl-mock_400w.jpg";

    public String getAllCDs() {
        Query query = manager.createQuery("SELECT c FROM CD c");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();

        return getResult(cdList);
    }

    public String getCD(long id) {
        Query query = manager.createQuery("SELECT c FROM CD c WHERE iD=" + id);
        Collection<CD> cdList = (Collection<CD>) query.getResultList();

        return getResult(cdList);
    }

    public String getCDByName(String name) {
        Query query = manager.createQuery("SELECT c FROM CD c WHERE name='" + name + "'");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();

        return getResult(cdList);
    }

    public String getCDByArtist(String name) {
        Query query = manager.createQuery("SELECT c FROM CD c WHERE artist='" + name + "'");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();

        return getResult(cdList);
    }

    public String getCDByGenre(String genre) {
        Query query = manager.createQuery("SELECT c FROM CD c WHERE genre='" + genre + "'");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();

        return getResult(cdList);
    }

    public String getCDByYear(String year) {
        Query query = manager.createQuery("SELECT c FROM CD c WHERE year='" + year + "'");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();

        return getResult(cdList);
    }

    public String deleteCD(String key, long id) {
        CD cdInDB = findCd(id);

        if (cdInDB != null) {

            if (!cdInDB.getAuthor().equals(key))
                return ERROR_JSON;

            manager.remove(cdInDB);
            return "{\"message\": \"cd sucessfully deleted\"}";
        }
        return "{\"Error\": \"Cd of that id doesn't exist\"}";

    }

    public String deleteAll() {
        Query query = manager.createQuery("DELETE FROM CD");
        query.executeUpdate();
        return "{\"message\": \"cds sucessfully deleted\"}";
    }

    public String updateCD(String key, long id, String data) {


        CD cdToUpdate = util.getObject(data, CD.class);
        CD cdInDB = findCd(id);

        if (cdInDB != null) {

            if (!cdInDB.getAuthor().equals(key))
                return ERROR_JSON;

            cdToUpdate.setiD(cdInDB.getID());
            if (cdToUpdate.getAuthor() == null)
                cdToUpdate.setAuthor(cdInDB.getAuthor());

            if (cdToUpdate.getArtist() == null)
                cdToUpdate.setArtist(cdInDB.getArtist());

            if (cdToUpdate.getYear() == null)
                cdToUpdate.setYear(cdInDB.getYear());

            if (cdToUpdate.getName() == null)
                cdToUpdate.setName(cdInDB.getName());

            if (cdToUpdate.getAlbumArt() == null)
                cdToUpdate.setAlbumArt(cdInDB.getAlbumArt());

            if (cdToUpdate.getGenre() == null)
                cdToUpdate.setGenre(cdInDB.getGenre());

            manager.merge(cdToUpdate);

            return "{\"message\": \"cd sucessfully updated\"}";
        }
        return "{\"Error\": \"Cd of that id doesn't exist\"}";

    }

    public String addCD(String key, String cd) {
        CD aCd = util.getObject(cd, CD.class);
        aCd.setAuthor(key);
        if(aCd.getAlbumArt()==null)aCd.setAlbumArt(NO_ARTWORK);
        manager.persist(aCd);
        return "{\"message\": \"cd sucessfully added\"}";
    }

    public String addCDs(String key, String cd) {
        CD[] cds = util.getObject(cd, CD[].class);
        for (CD aCd : cds) {
            aCd.setAuthor(key);
            if(aCd.getAlbumArt()==null)aCd.setAlbumArt(NO_ARTWORK);
            manager.persist(aCd);
        }
        return "{\"message\": \"cds sucessfully added\"}";
    }

    private CD findCd(Long id) {
        return manager.find(CD.class, id);
    }

    private String getResult(Collection<CD> cds) {

        String jsonString = "[";

        //removing authors
        for (CD cd : cds) {
            String author = cd.getAuthor();
            cd.setAuthor(null);
            jsonString += util.getJSONString(cd) + ",";
            cd.setAuthor(author);
        }

        if (jsonString.length() > 1)
            jsonString = jsonString.substring(0, jsonString.length() - 1);

        jsonString += "]";

        return jsonString;

    }

    private ExposedCD getECD(CD aCd) {
        return new ExposedCD(aCd.getID(), aCd.getName(), aCd.getArtist(), aCd.getGenre(), aCd.getYear());
    }

}
