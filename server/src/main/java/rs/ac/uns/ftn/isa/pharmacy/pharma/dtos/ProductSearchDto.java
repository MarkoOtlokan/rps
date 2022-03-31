package rs.ac.uns.ftn.isa.pharmacy.pharma.dtos;

import rs.ac.uns.ftn.isa.pharmacy.finance.Price;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;

public class ProductSearchDto {
    private long storedProductId;
    private long productId;
    private String name;
    private long quantity;
    private Price price;
    private String manufacturer;
    private long pharmacyId;
    private String pharmacyName;
    private String additionalNotes;
    private Product.IntakeType intakeType;
    private Product.Type productType;
    private double rating;

    public ProductSearchDto(long storedProductId, long productId, String name, long quantity, Price price, String manufacturer, long pharmacyId, String pharmacyName, String additionalNotes, Product.IntakeType intakeType, Product.Type productType, double rating) {
        this.storedProductId = storedProductId;
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.manufacturer = manufacturer;
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.additionalNotes = additionalNotes;
        this.intakeType = intakeType;
        this.productType = productType;
        this.rating = rating;
    }

    public long getStoredProductId() {
        return storedProductId;
    }

    public void setStoredProductId(long storedProductId) {
        this.storedProductId = storedProductId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public Product.IntakeType getIntakeType() {
        return intakeType;
    }

    public void setIntakeType(Product.IntakeType intakeType) {
        this.intakeType = intakeType;
    }

    public Product.Type getProductType() {
        return productType;
    }

    public void setProductType(Product.Type productType) {
        this.productType = productType;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
