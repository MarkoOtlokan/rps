package rs.ac.uns.ftn.isa.pharmacy.pharma.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.ProductSimpleDto;

public class ProductSimpleMapper {

    public static ProductSimpleDto objectToDto(Product product) {
        return new ProductSimpleDto(product.getId(), product.getName());
    }
}
