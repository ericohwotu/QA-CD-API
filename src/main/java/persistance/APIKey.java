package persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Administrator on 20/07/2017.
 */

@Entity
public class APIKey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String apiKey;
    private String user;

    APIKey(){}

    APIKey(String user, String key){
        this.apiKey = key;
        this.user = user;
    }

    public String getApiKey() {
        return apiKey;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
