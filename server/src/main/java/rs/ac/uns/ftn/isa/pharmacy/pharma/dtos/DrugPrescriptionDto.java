package rs.ac.uns.ftn.isa.pharmacy.pharma.dtos;

public class ProductPrescriptionDto {
    private long productId;
    private int duration;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
