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
    private String author;
    private String albumArt;


    public CD() {
    }

    public CD(String name, String artist, String genre, String year, String image) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
    }

    public long getID() {
        return iD;
    }

    public void setName(String input) {
        name = input;
    }

    public String getName() {
        return name;
    }

    public void setArtist(String input) {
        artist = input;
    }

    public String getArtist() {
        return artist;
    }

    public void setGenre(String input) {
        genre = input;
    }

    public String getGenre() {
        return genre;
    }

    public void setYear(String input) {
        year = input;
    }

    public String getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setiD(long iD) {
        this.iD = iD;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }
}
