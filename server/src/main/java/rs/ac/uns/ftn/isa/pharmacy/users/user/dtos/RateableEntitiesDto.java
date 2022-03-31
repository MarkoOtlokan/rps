package rs.ac.uns.ftn.isa.pharmacy.users.user.dtos;

import java.util.List;

public class RateableEntitiesDto {
    private List<RatingPharmacyDto> pharmacies;
    private List<RatingProductDto> products;

    public RateableEntitiesDto(List<RatingPharmacyDto> pharmacies,
                               List<RatingProductDto> products) {
        this.pharmacies = pharmacies;
        this.products = products;
    }

    public List<RatingPharmacyDto> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<RatingPharmacyDto> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public List<RatingProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<RatingProductDto> products) {
        this.products = products;
    }
}
