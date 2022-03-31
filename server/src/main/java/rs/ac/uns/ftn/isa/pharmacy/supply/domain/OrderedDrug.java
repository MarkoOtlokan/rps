package rs.ac.uns.ftn.isa.pharmacy.supply.domain;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;

import javax.persistence.*;

/**
 * Product which is being ordered by a Pharmacy through PurchaseOrder, in a specific amount.
 */
@Entity
@Table(name = "ordered_product")
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Product product;
    @OneToOne
    private PurchaseOrder order;
    private int amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PurchaseOrder getOrder() {
        return order;
    }

    public void setOrder(PurchaseOrder order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
