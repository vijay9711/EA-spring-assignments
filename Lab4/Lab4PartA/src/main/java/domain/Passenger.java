package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id")
    @OrderColumn(name = "flight_order") // This maintains the order of the flights
    private List<Flight> flights = new ArrayList<Flight>();

    // Constructors, getters, setters, etc
    public Passenger() {
    }
    public Passenger(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Flight> getFlights() {
        return flights;
    }
    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
    public void addFlight(Flight flight) {
        flights.add(flight);
    }
    public void removeFlight(Flight flight) {
        flights.remove(flight);
    }
    @Override
    public String toString() {
        return "Passenger --> " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flights=" + flights;
    }

}

