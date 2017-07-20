package business;

import persistance.CD;

import java.util.Collection;

/**
 * Created by Administrator on 20/07/2017.
 */
public interface CDService {

    String getAllCDs();

    void deleteCD(CD cd);

    void updateCD();

    void addCD(CD cd);

    //get cds by different fields
    Collection<CD> getCDsByName(String name);
    CD getCDByID(int id);
}
