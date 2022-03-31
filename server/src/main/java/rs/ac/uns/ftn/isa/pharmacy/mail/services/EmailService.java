package rs.ac.uns.ftn.isa.pharmacy.mail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.mail.messages.*;
import rs.ac.uns.ftn.isa.pharmacy.mail.dtos.ProductNonExistentMailInfo;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.ProductReservation;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredProduct;
import rs.ac.uns.ftn.isa.pharmacy.promotion.model.Promotion;
import rs.ac.uns.ftn.isa.pharmacy.promotion.model.Subscription;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;
    private static final String SENDER_EMAIL = "isa.pharmacy.app@gmail.com";


    public void sendProductReservedMessage(ProductReservation reservation) {
        var message = new ProductReservedMessage(emailSender, reservation);
        message.send(
                SENDER_EMAIL,
                reservation.getClient().getPerson().getCredentials().getEmail(),
                "Medication reserved."
        );
    }
    public void sendInsufficientProductQuantityMessage(StoredProduct storedProduct){
        var message = new InsufficientProductQuantityMessage(emailSender,storedProduct);
        message.send(
                SENDER_EMAIL,
                storedProduct.getPharmacy().getPharmacyAdmin().getPerson().getCredentials().getEmail(),
                "Insufficient product amount.");

    }
    public void sendProductNonExistentMessage(ProductNonExistentMailInfo mailInfo){
        var message = new ProductNonExistentMessage(emailSender,mailInfo);
        message.send(
                SENDER_EMAIL,
                mailInfo.getAdminMail(),
                "Product doesn't exist in our stock."
        );
    }
    public void sendProductDispensedMessage(ProductReservation productReservation){
        var message = new ProductDispensedMessage(emailSender,productReservation);
        message.send(
                SENDER_EMAIL,
                productReservation.getClient().getPerson().getCredentials().getEmail(),
                "Product successfully retrieved.");
    }


    public void sendActivationMessage(Credentials credentials) {
        var message = new ActivationMessage(emailSender, credentials);
        message.send(SENDER_EMAIL, credentials.getEmail(), "Activation");
    }

    public void sendPromotionMessage(Promotion promotion, Subscription subscription) {
        var message = new PromotionMessage(emailSender, promotion);
        message.send(
                SENDER_EMAIL,
                subscription.getPerson().getCredentials().getEmail(),
                "New promotion by " + promotion.getPharmacy().getName()
        );
    }
}
