package rs.ac.uns.ftn.isa.pharmacy.users.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="ratings")
public abstract class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Client client;
    private double rating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Invalid rating value.");
        }
        this.rating = rating;
    }
}
