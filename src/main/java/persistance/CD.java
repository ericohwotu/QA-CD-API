package persistance;

/*
 * Created by Eric
 * Edited by Duane
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CD {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long iD;
    private String name;
    private String artist;
    private String genre;
    private String year;


    public CD(){

    }

    public CD(String name, String artist, String genre, String year){
        this.name =name;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
    }

    long getID () {
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
