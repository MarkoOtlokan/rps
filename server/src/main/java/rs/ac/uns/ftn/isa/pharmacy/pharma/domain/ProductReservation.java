package rs.ac.uns.ftn.isa.pharmacy.pharma.domain;

import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Client;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.DateException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.QuantityException;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product_reservations")
public class ProductReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private StoredProduct storedProduct;
    private LocalDate pickUpBefore;
    private boolean isDispensed;
    private int quantity;

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

    public StoredProduct getStoredProduct() {
        return storedProduct;
    }

    public void setStoredProduct(StoredProduct storedProduct) {
        this.storedProduct = storedProduct;
    }

    public LocalDate getPickUpBefore() {
        return pickUpBefore;
    }

    public void setPickUpBefore(LocalDate pickUpBefore) {
        if (pickUpBefore.isBefore(LocalDate.now())) {
            throw new DateException();
        }
        this.pickUpBefore = pickUpBefore;
    }

    public int getQuantity() {
        if (quantity < 1) {
            throw new QuantityException();
        }
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isInPast() {
        return this.pickUpBefore.isBefore(LocalDate.now());
    }

    public boolean isInPast(int days) {
        return this.pickUpBefore.isBefore(LocalDate.now().plusDays(days));
    }

    public boolean canBeDispensed(){
        return !this.isInPast(1) || !this.isDispensed;
    }

    public boolean isDispensed() {
        return isDispensed;
    }

    public void setDispensed(boolean dispensed) {
        isDispensed = dispensed;
    }
}
