package rs.ac.uns.ftn.isa.pharmacy.pharma.domain;

import java.time.LocalDateTime;
import java.util.List;

public class EPrescription {

    private long  id;
    private String firstName;
    private String lastName;
    private LocalDateTime dispensingDate;
    private List<EPrescriptionProduct> prescribedProducts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getDispensingDate() {
        return dispensingDate;
    }

    public void setDispensingDate(LocalDateTime dispensingDate) {
        this.dispensingDate = dispensingDate;
    }

    public List<EPrescriptionProduct> getPrescribedProducts() {
        return prescribedProducts;
    }

    public void setPrescribedProducts(List<EPrescriptionProduct> prescribedProducts) {
        this.prescribedProducts = prescribedProducts;
    }
}
