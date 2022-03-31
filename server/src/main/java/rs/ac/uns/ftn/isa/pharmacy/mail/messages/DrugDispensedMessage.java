package rs.ac.uns.ftn.isa.pharmacy.mail.messages;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.ProductReservation;

import java.text.MessageFormat;

public class ProductDispensedMessage extends EmailMessage<ProductReservation>{
    public ProductDispensedMessage(JavaMailSender mailSender, ProductReservation entity) {
        super(mailSender, entity);
    }

    @Override
    protected String formatMessage(ProductReservation entity) {
        String message = "Dear {0} {1}, \n\nYou have successfully retrieved {2}" +
                ", quantity: {3}, from pharmacy: \"{4}\".";
        return MessageFormat.format(message,
                entity.getClient().getPerson().getFirstName(),
                entity.getClient().getPerson().getLastName(),
                entity.getStoredProduct().getProduct().getName(),
                entity.getQuantity(),
                entity.getStoredProduct().getPharmacy().getName()
        );
    }
}
