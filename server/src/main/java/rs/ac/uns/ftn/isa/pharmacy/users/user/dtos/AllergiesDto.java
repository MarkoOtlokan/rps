package rs.ac.uns.ftn.isa.pharmacy.users.user.dtos;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class AllergiesDto {
    private List<Product> allergicTo;

    public AllergiesDto(List<Product> allergicTo) {
        this.allergicTo = allergicTo;
    }

    public AllergiesDto() {
        this.allergicTo = new ArrayList<Product>();
    }

    public List<Product> getAllergicTo() {
        return allergicTo;
    }

    public void setAllergicTo(List<Product> allergicTo) {
        this.allergicTo = allergicTo;
    }
}
