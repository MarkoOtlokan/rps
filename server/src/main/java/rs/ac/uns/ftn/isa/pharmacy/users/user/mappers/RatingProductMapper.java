package rs.ac.uns.ftn.isa.pharmacy.users.user.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.ProductRating;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.RatingProductDto;

public class RatingProductMapper {
    public static RatingProductDto objectToDto(Product product) {
        return new RatingProductDto(
                product.getId(),
                product.getName(),
                product.getRating(),
                product.getManufacturer()
        );
    }

    public static ProductRating dtoToObject(RatingProductDto dto) {
        var productRating = new ProductRating();
        productRating.setProduct(new Product());
        productRating.getProduct().setId(dto.getId());
        productRating.setRating(dto.getRating());
        return productRating;
    }
}
