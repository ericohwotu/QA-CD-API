package business;

import persistance.CD;
import util.JSONUtil;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Stateless
@Default
public class CDServiceImpl implements CDService{

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    @Inject
    private JSONUtil util;

    public String getAllCDs () {
        Query query = manager.createQuery("SELECT c FROM CD c");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();
        return util.getJSONString(cdList);
    }

    public String getCD(long id){
        Query query = manager.createQuery("SELECT c FROM CD c WHERE iD="+id);
        Collection<CD> cdList = (Collection<CD>) query.getResultList();
        return util.getJSONString(cdList);
    }

    public String getCD(String name){
        Query query = manager.createQuery("SELECT c FROM CD c WHERE name='"+name+"'");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();
        return util.getJSONString(cdList);
    }

    public String deleteCD(long id) {
        CD movieInDB = findCd(id);
        if (movieInDB != null) {
            manager.remove(movieInDB);
        }
        return "{\"message\": \"cd sucessfully deleted\"}";
    }

    public String deleteAll() {
        Query query = manager.createQuery("DELETE FROM CD");
         query.executeUpdate();
        return "{\"message\": \"cds sucessfully deleted\"}";
    }

    public String updateCD(long id, String data) {

        CD cdToUpdate = util.getObject(data, CD.class);
        CD cdInDB = findCd(id);

        if (cdInDB != null) {
            manager.merge(cdToUpdate);
        }
        return "{\"message\": \"cd sucessfully updated\"}";
    }

    public String addCD(String cd) {
        CD aCd = util.getObject(cd, CD.class);
        manager.persist(aCd);
        return "{\"message\": \"cd sucessfully added\"}";
    }

    private CD findCd(Long id) {
        return manager.find(CD.class, id);
    }

    public Collection<CD> getCDsByName(String name) {
        return null;
    }

    public Collection<CD> getCDByID(int id) {
        return null;
    }
}
