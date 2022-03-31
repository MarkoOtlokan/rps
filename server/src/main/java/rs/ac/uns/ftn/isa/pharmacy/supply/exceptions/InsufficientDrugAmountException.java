package rs.ac.uns.ftn.isa.pharmacy.supply.exceptions;

public class InsufficientProductAmountException extends MessageException {

    public InsufficientProductAmountException() {
        super("Insufficient products in stock.");
    }
}
