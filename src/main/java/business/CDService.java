package business;

import persistance.CD;

import java.util.Collection;

public interface CDService {

    String getAllCDs();

    String deleteCD(long id);

    String updateCD(long id, String str);

    String addCD(String cd);

    String getCD(long id);

    String getCD(String name);

    String deleteAll();

}
