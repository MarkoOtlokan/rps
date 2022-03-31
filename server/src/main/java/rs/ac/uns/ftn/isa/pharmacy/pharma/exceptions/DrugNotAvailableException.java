package rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions;

import javax.persistence.PersistenceException;

public class ProductNotAvailableException extends PersistenceException {
    public ProductNotAvailableException(){
        super("Required product is not available at this moment.");
    }
}
