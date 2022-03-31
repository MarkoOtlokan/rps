package rs.ac.uns.ftn.isa.pharmacy.pharma.dtos;

import rs.ac.uns.ftn.isa.pharmacy.finance.Price;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;

public class StoredProductDto {
    private long storedProductId;
    private Price price;
    private Product product;
    private Pharmacy pharmacy;

    public StoredProductDto(long storedProductId, Price price, Product product, Pharmacy pharmacy) {
        this.storedProductId = storedProductId;
        this.price = price;
        this.product = product;
        this.pharmacy = pharmacy;
    }

    public long getStoredProductId() {
        return storedProductId;
    }

    public void setStoredProductId(long storedProductId) {
        this.storedProductId = storedProductId;
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
}
