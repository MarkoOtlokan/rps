package rs.ac.uns.ftn.isa.pharmacy.supply.dto;

import rs.ac.uns.ftn.isa.pharmacy.supply.domain.PurchaseOrder;

import java.util.stream.Collectors;

public class PurchaseOrderMapper {
    public static PurchaseOrderOverviewDto objectToDto(PurchaseOrder purchaseOrder) {
        return new PurchaseOrderOverviewDto(
                purchaseOrder.getId(),
                purchaseOrder.getOfferDeadline(),
                purchaseOrder.getOrderedProducts().stream().map(OrderedProductMapper::objectToDto).collect(Collectors.toList())
        );
    }
}
