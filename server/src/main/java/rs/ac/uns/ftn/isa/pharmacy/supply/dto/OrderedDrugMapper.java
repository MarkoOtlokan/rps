package rs.ac.uns.ftn.isa.pharmacy.supply.dto;

import rs.ac.uns.ftn.isa.pharmacy.supply.domain.OrderedProduct;

public class OrderedProductMapper {
    public static PurchaseOrderOverviewDto.OrderedProductOverviewDto objectToDto(OrderedProduct orderedProduct) {
        return new PurchaseOrderOverviewDto.OrderedProductOverviewDto(
                orderedProduct.getAmount(), orderedProduct.getProduct().getName()
        );
    }
}
