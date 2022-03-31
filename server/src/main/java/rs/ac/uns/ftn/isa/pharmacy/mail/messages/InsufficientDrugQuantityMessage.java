package rs.ac.uns.ftn.isa.pharmacy.mail.messages;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredProduct;

import java.text.MessageFormat;

public class InsufficientProductQuantityMessage extends EmailMessage<StoredProduct> {
    public InsufficientProductQuantityMessage(JavaMailSender mailSender, StoredProduct entity) {
        super(mailSender, entity);
    }

    @Override
    protected String formatMessage(StoredProduct entity) {
        String message = "Dear {0},\n\nThere was a need for a medicine {1} recently, " +
                "but it was out of stock.\nConsider opening a procurement for the listed product.";
        return MessageFormat.format(message,
                entity.getPharmacy().getPharmacyAdmin().getPerson().getFirstName() + " "
                        + entity.getPharmacy().getPharmacyAdmin().getPerson().getLastName()
                , entity.getProduct().getName());
    }
}
