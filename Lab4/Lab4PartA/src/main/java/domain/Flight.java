package domain;

import jakarta.persistence.*;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private String fromLocation;
    private String toLocation;
    private String date;

    // Constructors, getters, setters, etc.

    // Constructor without parameters (for JPA)
    public Flight() {
    }

    // Constructor with parameters
    public Flight(String flightNumber, String fromLocation, String toLocation, String date) {
        this.flightNumber = flightNumber;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.date = date;
    }

    // Getters and setters

    // toString method for better representation
    @Override
    public String toString() {
        return "Flight --> " +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                ", date='" + date;
    }
}
