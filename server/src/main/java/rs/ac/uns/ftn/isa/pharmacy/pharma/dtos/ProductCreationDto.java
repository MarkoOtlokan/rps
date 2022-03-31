package rs.ac.uns.ftn.isa.pharmacy.pharma.dtos;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;

import java.util.List;

public class ProductCreationDto {

    private Product product;
    private List<Integer> alternativeProductIds;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Integer> getAlternativeProductIds() {
        return alternativeProductIds;
    }

    public void setAlternativeProductIds(List<Integer> alternativeProductIds) {
        this.alternativeProductIds = alternativeProductIds;
    }
}
