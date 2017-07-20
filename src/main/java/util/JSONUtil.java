package util;

import com.google.gson.Gson;

/**
 * Created by Administrator on 20/07/2017.
 */
public class JSONUtil {
    private Gson gson;

    public JSONUtil(){
        this.gson = new Gson();
    }

    public String getJSONString(Object obj){
        return gson.toJson(obj);
    }

    public <T> T getObject(String jsonString, Class<T> clss){
        return gson.fromJson(jsonString, clss);
    }
}
