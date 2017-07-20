package business;

import persistance.CD;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Stateless
@Default
public class CDServiceImpl implements CDService{

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    public String getAllCDs () {
        Query query = manager.createQuery("SELECT m FROM Movie m");
        Collection<CD> cdList = (Collection<CD>) query.getResultList();
        System.out.println(cdList);
        return "CD List Printer";
    }

    public String deleteCD(int id) {
        return "";
    }

    public String updateCD(int id) {
        return "";
    }

    public String addCD(String cd) {
        return "CD Added";
    }

    public Collection<CD> getCDsByName(String name) {
        return null;
    }

    public Collection<CD> getCDByID(int id) {
        return null;
    }
}
