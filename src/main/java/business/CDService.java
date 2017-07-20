package business;

import persistance.CD;

import java.util.Collection;

public interface CDService {

    String addCD(String key, String cd);

    String addCDs(String key, String cd);

    String getAllCDs();

    String getCD(long id);

    String getCDByName(String name);

    String getCDByArtist(String title);

    String getCDByGenre(String genre);

    String getCDByYear(String year);

    String deleteCD(String key, long id);

    String deleteAll();

    String updateCD(String key, long id, String str);

}
