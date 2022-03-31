package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.ProductReservation;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredProduct;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.ProductReservationDto;

public class ProductReservationMapper {
    public static ProductReservation dtoToObject(ProductReservationDto dto) {
        ProductReservation object = new ProductReservation();
        object.setDispensed(false);
        object.setQuantity(dto.getQuantity());
        var storedProduct = new StoredProduct();
        storedProduct.setId(dto.getStoredProductId());
        object.setStoredProduct(storedProduct);
        object.setPickUpBefore(dto.getPickUpBefore());
        return object;
    }

    public static ProductReservationDto objectToDto(ProductReservation reservation) {
        return new ProductReservationDto(
                reservation.getId(),
                reservation.getStoredProduct().getProduct().getName(),
                reservation.getStoredProduct().getProduct().getManufacturer(),
                reservation.getStoredProduct().getPharmacy().getName(),
                reservation.getStoredProduct().getPrice(),
                reservation.getPickUpBefore(),
                reservation.getQuantity()
        );
    }
}
