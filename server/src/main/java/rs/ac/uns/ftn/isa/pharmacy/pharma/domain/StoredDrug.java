package rs.ac.uns.ftn.isa.pharmacy.pharma.domain;

import rs.ac.uns.ftn.isa.pharmacy.finance.Price;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.QuantityException;

import javax.persistence.*;

@Entity
@Table(name = "stored_products")
public class StoredProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Version
    private int version;
    private long quantity;
    @Embedded
    private Price price;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Pharmacy pharmacy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        if (quantity < 0) {
            throw new QuantityException("There is not enough of the stored product.");
        }
        this.quantity = quantity;
    }
    public void decrementQuantity(){
        if (quantity < 0) {
            throw new QuantityException("There is not enough of the stored product.");
        }
        this.quantity=-1;

    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
