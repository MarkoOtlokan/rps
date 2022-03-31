package rs.ac.uns.ftn.isa.pharmacy.supply.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseOrderOverviewDto {
    private long id;
    private LocalDateTime offerDeadline;
    private List<OrderedProductOverviewDto> orderedProducts;

    public PurchaseOrderOverviewDto(long id, LocalDateTime offerDeadline, List<OrderedProductOverviewDto> orderedProducts) {
        this.id = id;
        this.offerDeadline = offerDeadline;
        this.orderedProducts = orderedProducts;
    }

    public static class OrderedProductOverviewDto {
        private int amount;
        private String productName;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public OrderedProductOverviewDto(int amount, String productName) {
            this.amount = amount;
            this.productName = productName;
        }


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getOfferDeadline() {
        return offerDeadline;
    }

    public void setOfferDeadline(LocalDateTime offerDeadline) {
        this.offerDeadline = offerDeadline;
    }

    public List<OrderedProductOverviewDto> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderedProductOverviewDto> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
}
