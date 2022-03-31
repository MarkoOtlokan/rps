package rs.ac.uns.ftn.isa.pharmacy.pharma.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.ProductRating;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Rating;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@SequenceGenerator(name = "product_seq", initialValue = 100)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private long id;
    private String name;
    private Product.Type productType;
    private Product.IntakeType intakeType;
    @ElementCollection
    private List<String> ingredients;
    private String manufacturer;
    private boolean requiresPrescription;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> alternatives;
    private String additionalNotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy ="product")
    @JsonIgnore
    private List<ProductRating> ratings;

    public void validate() throws InvalidEntityException {
        if (name == null || name.isEmpty()) throw new InvalidEntityException("Name");
        if (productType == null) throw new InvalidEntityException("Product type");
        if (intakeType == null) throw new InvalidEntityException("Intake type");
        if (ingredients == null || ingredients.isEmpty()) throw new InvalidEntityException("Ingredients");
        if (manufacturer == null || manufacturer.isEmpty()) throw new InvalidEntityException("Manufacturer");
        if (additionalNotes == null) throw new InvalidEntityException("Additional notes");
    }

    public enum IntakeType {
        CAPSULE, POWDER, OINTMENT, TABLET, PASTE, GEL, SYRUP, SOLUTION
    }

    public enum Type {
        NERVE, CARDIO, RESPIRATORY, BLOOD, ANTIINFECTANT
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getProductType() {
        return productType;
    }

    public void setProductType(Type productType) {
        this.productType = productType;
    }

    public IntakeType getIntakeType() {
        return intakeType;
    }

    public void setIntakeType(IntakeType intakeType) {
        this.intakeType = intakeType;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isRequiresPrescription() {
        return requiresPrescription;
    }

    public void setRequiresPrescription(boolean requiresPrescription) {
        this.requiresPrescription = requiresPrescription;
    }

    public List<Product> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Product> alternatives) {
        this.alternatives = alternatives;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public double getRating() {
        double totalRating = 0;
        int size = this.ratings.size();
        if (size == 0) return 0;
        for (var rating: this.ratings) {
            totalRating += rating.getRating();
        }
        return totalRating / size;
    }

    public List<ProductRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<ProductRating> ratings) {
        this.ratings = ratings;
    }
}
