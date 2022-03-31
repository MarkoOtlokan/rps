package rs.ac.uns.ftn.isa.pharmacy.users.user.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.ProductReservationService;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Client;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.ProductRating;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.EmployeeRating;

import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.PharmacyRating;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.ProductRatingRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.EmployeeRatingRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.PharmacyRatingRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RatingService {
    private final EmployeeRatingRepository employeeRatingRepository;
    private final ProductRatingRepository productRatingRepository;
    private final PharmacyRatingRepository pharmacyRatingRepository;
    private final ProductReservationService productReservationService;

    public RatingService(ProductReservationService productReservationService,
                         EmployeeRatingRepository employeeRatingRepository,
                         ProductRatingRepository productRatingRepository,
                         PharmacyRatingRepository pharmacyRatingRepository) {
        this.productReservationService = productReservationService;
        this.employeeRatingRepository = employeeRatingRepository;
        this.productRatingRepository = productRatingRepository;
        this.pharmacyRatingRepository = pharmacyRatingRepository;
    }

    public List<Product> getClientProductHistory(long clientId) {
        Map<Long, Product> productMap = new HashMap<>();
        for (var productReservation: productReservationService.findClientReservationHistory(clientId)) {
            productMap.put(productReservation.getStoredProduct().getProduct().getId(), productReservation.getStoredProduct().getProduct());
        }
        return new ArrayList(productMap.values());
    }

    public List<Pharmacy> getClientPharmacyHistory(long clientId) {
        Map<Long, Pharmacy> pharmacyMap = new HashMap<>();
        for (var productReservations: productReservationService.findClientReservationHistory(clientId)) {
            pharmacyMap.put(productReservations.getStoredProduct().getPharmacy().getId(),
                    productReservations.getStoredProduct().getPharmacy());
        }
        return new ArrayList(pharmacyMap.values());
    }


    public void rateEmployee(EmployeeRating rating, long clientId) {
        rating.setClient(new Client());
        rating.getClient().setId(clientId);
        var existingRating
                = employeeRatingRepository.findByEmployeeIdAndClientId(rating.getEmployee().getId(), clientId);
        if (existingRating.isPresent()) {
            rating.setId(existingRating.get().getId());
        }
        employeeRatingRepository.save(rating);
    }

    public void rateProduct(ProductRating rating, long clientId) {
        rating.setClient(new Client());
        rating.getClient().setId(clientId);
        var existingRating
                = productRatingRepository.findByProductIdAndClientId(rating.getProduct().getId(), clientId);
        if (existingRating.isPresent()) {
            rating.setId(existingRating.get().getId());
        }
        productRatingRepository.save(rating);
    }

    public void ratePharmacy(PharmacyRating rating, long clientId) {
        rating.setClient(new Client());
        rating.getClient().setId(clientId);
        var existingRating
                = pharmacyRatingRepository.findByPharmacyIdAndClientId(rating.getPharmacy().getId(), clientId);
        if (existingRating.isPresent()) {
            rating.setId(existingRating.get().getId());
        }
        pharmacyRatingRepository.save(rating);
    }
}
