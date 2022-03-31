package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredProduct;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.ProductSearchDto;

public class ProductSearchMapper {
    public static ProductSearchDto objectToDto(StoredProduct storedProduct) {
        return new ProductSearchDto(
                storedProduct.getId(),
                storedProduct.getProduct().getId(),
                storedProduct.getProduct().getName(),
                storedProduct.getQuantity(),
                storedProduct.getPrice(),
                storedProduct.getProduct().getManufacturer(),
                storedProduct.getPharmacy().getId(),
                storedProduct.getPharmacy().getName(),
                storedProduct.getProduct().getAdditionalNotes(),
                storedProduct.getProduct().getIntakeType(),
                storedProduct.getProduct().getProductType(),
                storedProduct.getProduct().getRating()
        );
    }
}
