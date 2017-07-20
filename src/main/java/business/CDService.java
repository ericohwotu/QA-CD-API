package business;

import persistance.CD;

import java.util.Collection;

public interface CDService {

    Collection<CD> getAllCDs();

    String deleteCD(int id);

    String updateCD(int id);

    String addCD(String cd);

    //get cds by different fields
    Collection<CD> getCDsByName(String name);
    Collection<CD> getCDByID(int id);
}
