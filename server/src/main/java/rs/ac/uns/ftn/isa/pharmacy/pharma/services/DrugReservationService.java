package rs.ac.uns.ftn.isa.pharmacy.pharma.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.UserAccessException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.ProductReservation;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredProduct;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.AllergyException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.DateException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.StoredProductRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.repository.EmployeeRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.ProductReservationRepository;
import rs.ac.uns.ftn.isa.pharmacy.mail.services.EmailService;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Client;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.ClientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductReservationService {

    private final ProductReservationRepository reservationRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;
    private final ClientRepository clientRepository;
    private final StoredProductRepository storedProductRepository;

    public ProductReservationService(ProductReservationRepository reservationRepository,
                                  EmployeeRepository employeeRepository,
                                  EmailService emailService,
                                  ClientRepository clientRepository,
                                  StoredProductRepository storedProductRepository) {
        this.reservationRepository = reservationRepository;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
        this.clientRepository = clientRepository;
        this.storedProductRepository = storedProductRepository;
    }

    public ProductReservation getReserved(long productReservationId, long pharmacistId){
        var reservedProduct = reservationRepository.findById(productReservationId)
                .orElseThrow(() -> new EntityNotFoundException(ProductReservation.class.getSimpleName(),productReservationId));
        var pharmacist = employeeRepository.getOne(pharmacistId);

        if(!pharmacist.worksIn(reservedProduct.getStoredProduct().getPharmacy()))
            throw new EntityNotFoundException(ProductReservation.class.getSimpleName(),productReservationId);

        if(!reservedProduct.canBeDispensed())
            throw new DateException("Product cannot be dispensed.");

        return reservedProduct;

    }
    public void dispense(long productReservationId){
        var productReservation = reservationRepository.findById(productReservationId)
                .orElseThrow(() -> new EntityNotFoundException(ProductReservation.class.getSimpleName(),productReservationId));
        emailService.sendProductDispensedMessage(productReservation);
        productReservation.setDispensed(true);
        reservationRepository.save(productReservation);
    }

    @Transactional
    public void updateStoredProductQuantity(long storedProductId, int quantity) {
        var storedProduct = storedProductRepository.findById(storedProductId)
                .orElseThrow(() -> new EntityNotFoundException(StoredProduct.class.getSimpleName(), storedProductId));
        storedProduct.setQuantity(storedProduct.getQuantity() + quantity);
        storedProductRepository.save(storedProduct);
    }

    @Transactional
    public void reserve(ProductReservation productReservation, long clientId) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException(Client.class.getSimpleName(), clientId));
        var storedProduct = storedProductRepository.findById(productReservation.getStoredProduct().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        StoredProduct.class.getSimpleName(),
                        productReservation.getStoredProduct().getId())
                );
        if (productReservation.isInPast()) {
            throw new DateException();
        }
        productReservation.setClient(client);
        productReservation.setStoredProduct(storedProduct);
        updateStoredProductQuantity(productReservation.getStoredProduct().getId(), -productReservation.getQuantity());
        try {
            ProductReservation reservation = reservationRepository.save(productReservation);
            emailService.sendProductReservedMessage(reservation);
        } catch (Exception e) {
            updateStoredProductQuantity(productReservation.getStoredProduct().getId(), productReservation.getQuantity());
            throw e;
        }
    }

    public void cancelReservation(long productReservationId, long clientId) {
        var productReservation = reservationRepository.findById(productReservationId)
                .orElseThrow(() -> new EntityNotFoundException(ProductReservation.class.getSimpleName(), productReservationId));
        if (productReservation.getClient().getId() != clientId) {
            throw new UserAccessException();
        }
        if (productReservation.isInPast(1)) {
            throw new DateException("Reservation cannot be canceled.");
        }
        updateStoredProductQuantity(productReservation.getStoredProduct().getId(), productReservation.getQuantity());
        try {
            reservationRepository.deleteById(productReservationId);
        } catch (Exception e) {
            updateStoredProductQuantity(productReservation.getStoredProduct().getId(), -productReservation.getQuantity());
            throw e;
        }
    }

    public List<ProductReservation> findExpired(LocalDate now) {
        return reservationRepository.findExpired(now);
    }

    public List<ProductReservation> findClientReservations(long clientId) {
        return reservationRepository.findAllByClientId(clientId).stream()
                .filter(res -> !res.isDispensed())
                .collect(Collectors.toList());
    }

    public void deleteById(long id) {
        reservationRepository.deleteById(id);
    }

    public List<ProductReservation> findClientReservationHistory(long clinetId) {
        return reservationRepository.findAllByClientId(clinetId).stream()
                .filter(res -> res.isDispensed())
                .collect(Collectors.toList());
    }
}
