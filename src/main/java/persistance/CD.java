package persistance;

/*
 * Created by Eric
 * Edited by Duane
 */

import javax.persistence.Entity;

@Entity
public class CD {
    private int iD;
    private String name;
    private String artist;
    private String genre;
    private String year;

    void setID (int input) {
        iD = input;
    }
    int getID () {
        return iD;
    }
    void setName (String input) {
        name = input;
    }
    String getName () {
        return name;
    }
    void setArtist (String input) {
        artist = input;
    }
    String getArtist () {
        return artist;
    }
    void setGenre (String input) {
        genre = input;
    }
    String getGenre () {
        return genre;
    }
    void setYear (String input) {
        year = input;
    }
    String getYear () {
        return year;
    }

}
