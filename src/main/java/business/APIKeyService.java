package business;
/**
 * Created by Administrator on 20/07/2017.
 */


public interface APIKeyService {
    String genAPIKey(String user);
    boolean checkAPIKey(String key);
    String isApiCorrect(String key);
}
