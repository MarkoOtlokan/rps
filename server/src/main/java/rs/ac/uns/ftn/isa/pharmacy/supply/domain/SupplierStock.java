package rs.ac.uns.ftn.isa.pharmacy.supply.domain;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;

import javax.persistence.*;

/**
 * Amount of a specific product which a given Supplier has in stock at the moment.
 */
@Entity
@Table(name = "supplier_product_stock")
public class SupplierStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Supplier supplier;
    @OneToOne
    private Product product;
    private int amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
