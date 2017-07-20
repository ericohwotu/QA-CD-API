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

    public String deleteCD(int id) {
        return "";
    }

    public String updateCD(int id) {
        return "";
    }

    public String addCD(String cd) {
        CD aCd = util.getObject(cd, CD.class);
        manager.persist(aCd);
        return "{\"message\": \"movie sucessfully added\"}";
    }

    public Collection<CD> getCDsByName(String name) {
        return null;
    }

    public Collection<CD> getCDByID(int id) {
        return null;
    }
}
