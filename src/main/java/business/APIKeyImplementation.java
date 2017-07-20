package business;

import persistance.APIKey;
import util.JSONUtil;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by Administrator on 20/07/2017.
 */

@Stateless
@Default

public class APIKeyImplementation implements APIKeyService {

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    @Inject
    private JSONUtil util;

    private String getKey(int limit){
        String spectrum = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
        String key = "";
        for(int i=0; i<=limit; i++){
            int rand = (int)Math.floor(Math.random()*spectrum.length());
            key += spectrum.charAt(rand);
        }
        return key;
    }

    @Override
    public String genAPIKey(String user){
        String json = "{\"user\":\"" + user + "\",";
        json +=  "\"apiKey\":\"" + getKey(32) + "\"}";
        APIKey aKey = util.getObject(json, APIKey.class);
        manager.persist(aKey);
        return json;
    }

    public boolean checkAPIKey(String key){
        Query query = manager.createQuery("SELECT c FROM APIKey c WHERE apiKey='"+key+"'");
        Collection<APIKey> cdList = (Collection<APIKey>) query.getResultList();

        if (cdList.isEmpty())
            return false;
        else
            return true;
    }

    public String isApiCorrect(String key){
        Query query = manager.createQuery("SELECT c FROM APIKey c WHERE apiKey='"+key+"'");
        Collection<APIKey> cdList = (Collection<APIKey>) query.getResultList();

        if (cdList.isEmpty())
            return "{\"messahe\":\"false\"}";
        else
            return "{\"messahe\":\"true\"}";
    }
}
