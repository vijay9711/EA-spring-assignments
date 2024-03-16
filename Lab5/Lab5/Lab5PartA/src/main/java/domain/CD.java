package domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CD")
public class CD extends Product{
    private String artist;
    private String company;

    public CD(){}

    public CD(String name, String description, double price, String artist){
        super(name, description, price);
        this.artist=artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
