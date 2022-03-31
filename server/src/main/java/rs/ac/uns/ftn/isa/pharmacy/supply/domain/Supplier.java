package rs.ac.uns.ftn.isa.pharmacy.supply.domain;

import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    private long personId;
    @JoinColumn(name = "person_id")
    @OneToOne()
    private Person person;
    @OneToMany
    private List<SupplierStock> productsInStock;
    @OneToMany
    private List<Offer> offers;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<SupplierStock> getProductsInStock() {
        return productsInStock;
    }

    public void setProductsInStock(List<SupplierStock> productsInStock) {
        this.productsInStock = productsInStock;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
