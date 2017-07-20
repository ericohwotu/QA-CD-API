package persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Administrator on 20/07/2017.
 */

@Entity
public class ExposedCD {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long iD;
    private String name;
    private String artist;
    private String genre;
    private String year;

    public ExposedCD(long id, String name, String artist, String genre, String year){
        this.iD = id;
        this.name =name;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
    }
}
