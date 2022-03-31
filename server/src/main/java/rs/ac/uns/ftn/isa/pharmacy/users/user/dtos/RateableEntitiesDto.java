package rs.ac.uns.ftn.isa.pharmacy.users.user.dtos;

import java.util.List;

public class RateableEntitiesDto {
    private List<RatingPharmacyDto> pharmacies;
    private List<RatingDrugDto> drugs;

    public RateableEntitiesDto(List<RatingPharmacyDto> pharmacies,
                               List<RatingDrugDto> drugs) {
        this.pharmacies = pharmacies;
        this.drugs = drugs;
    }

    public List<RatingPharmacyDto> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<RatingPharmacyDto> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public List<RatingDrugDto> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<RatingDrugDto> drugs) {
        this.drugs = drugs;
    }
}
