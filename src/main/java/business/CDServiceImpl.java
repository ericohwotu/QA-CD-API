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
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

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

    public String getAllCDs() {
        Query query = manager.createQuery("SELECT c FROM CD c");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();
        return util.getJSONString(cdList);
    }

    public String getCD(long id) {
        Query query = manager.createQuery("SELECT c FROM CD c WHERE iD=" + id);
        Collection<CD> cdList = (Collection<CD>) query.getResultList();
        return util.getJSONString(cdList);
    }

    public String getCDByName(String name) {
        Query query = manager.createQuery("SELECT c FROM CD c WHERE name='" + name + "'");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();
        return util.getJSONString(cdList);
    }

    public String getCDByArtist(String name) {
        Query query = manager.createQuery("SELECT c FROM CD c WHERE artist='" + name + "'");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();
        return util.getJSONString(cdList);
    }

    public String getCDByGenre(String genre) {
        Query query = manager.createQuery("SELECT c FROM CD c WHERE genre='" + genre + "'");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();

        return util.getJSONString(cdList);
    }

    public String getCDByYear(String year) {
        Query query = manager.createQuery("SELECT c FROM CD c WHERE year='" + year + "'");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();
        return util.getJSONString(cdList);
    }

    public String deleteCD(String key, long id) {
        CD cdInDB = findCd(id);
        if (cdInDB != null) {

            if(!cdInDB.getAuthor().equals(key))
                return ERROR_JSON;

            manager.remove(cdInDB);
        }
        return "{\"message\": \"cd sucessfully deleted\"}";
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

            if(cdInDB.getAuthor().equals(key))
                return ERROR_JSON;

            manager.merge(cdToUpdate);
        }
        return "{\"message\": \"cd sucessfully updated\"}";
    }

    public String addCD(String key, String cd) {
        CD aCd = util.getObject(cd, CD.class);
        aCd.setAuthor(key);
        manager.persist(aCd);
        return "{\"message\": \"cd sucessfully added\"}";
    }

    public String addCDs(String key, String cd) {
        CD[] cds = util.getObject(cd, CD[].class);
        for (CD aCd : cds) {
            aCd.setAuthor(key);
            manager.persist(aCd);
        }
        return "{\"message\": \"cds sucessfully added\"}";
    }

    private CD findCd(Long id) {
        return manager.find(CD.class, id);
    }

    private String getResult(Collection<CD> cds){
        Collection<ExposedCD> exposedCDS = Collections.emptyList();
        for (CD cd : cds){
            exposedCDS.add(new ExposedCD(cd.getID(),cd.getName(),cd.getArtist(),cd.getGenre(),cd.getYear()));
        }
        return util.getJSONString(exposedCDS);
    }

    private ExposedCD getECD(CD aCd){
        return new ExposedCD(aCd.getID(),aCd.getName(),aCd.getArtist(),aCd.getGenre(),aCd.getYear());
    }

}
