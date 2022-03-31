package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredProduct;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.StoredProductDto;

public class StoredProductMapper {
    public static StoredProductDto objectToDto(StoredProduct storedProduct) {
        return new StoredProductDto(
                storedProduct.getId(),
                storedProduct.getPrice(),
                storedProduct.getProduct(),
                storedProduct.getPharmacy()
        );
    }
}
