package rs.ac.uns.ftn.isa.pharmacy.mail.messages;

import org.springframework.mail.javamail.JavaMailSender;
import rs.ac.uns.ftn.isa.pharmacy.mail.dtos.ProductNonExistentMailInfo;

import java.text.MessageFormat;

public class ProductNonExistentMessage extends EmailMessage<ProductNonExistentMailInfo> {
    public ProductNonExistentMessage(JavaMailSender mailSender, ProductNonExistentMailInfo entity) {
        super(mailSender, entity);
    }

    @Override
    protected String formatMessage(ProductNonExistentMailInfo entity) {
        String message = "Dear {0} ,\n\nThere was a need for a medicine {1} recently, " +
                "but it didn't exist in our stock.\nConsider opening a procurement for the listed product.";
        return MessageFormat.format(message, entity.getAdminFullName(),entity.getProductName());
    }
}
