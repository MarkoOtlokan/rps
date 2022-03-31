package rs.ac.uns.ftn.isa.pharmacy.users.user.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.DrugReservationService;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Client;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.DrugRating;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.EmployeeRating;

import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.PharmacyRating;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.DrugRatingRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.EmployeeRatingRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.PharmacyRatingRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RatingService {
    private final EmployeeRatingRepository employeeRatingRepository;
    private final DrugRatingRepository drugRatingRepository;
    private final PharmacyRatingRepository pharmacyRatingRepository;
    private final DrugReservationService drugReservationService;

    public RatingService(DrugReservationService drugReservationService,
                         EmployeeRatingRepository employeeRatingRepository,
                         DrugRatingRepository drugRatingRepository,
                         PharmacyRatingRepository pharmacyRatingRepository) {
        this.drugReservationService = drugReservationService;
        this.employeeRatingRepository = employeeRatingRepository;
        this.drugRatingRepository = drugRatingRepository;
        this.pharmacyRatingRepository = pharmacyRatingRepository;
    }

    public List<Drug> getPatientDrugHistory(long patientId) {
        Map<Long, Drug> drugMap = new HashMap<>();
        for (var drugReservation: drugReservationService.findPatientReservationHistory(patientId)) {
            drugMap.put(drugReservation.getStoredDrug().getDrug().getId(), drugReservation.getStoredDrug().getDrug());
        }
        return new ArrayList(drugMap.values());
    }

    public List<Pharmacy> getPatientPharmacyHistory(long patientId) {
        Map<Long, Pharmacy> pharmacyMap = new HashMap<>();
        for (var drugReservations: drugReservationService.findPatientReservationHistory(patientId)) {
            pharmacyMap.put(drugReservations.getStoredDrug().getPharmacy().getId(),
                    drugReservations.getStoredDrug().getPharmacy());
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

    public void rateDrug(DrugRating rating, long clientId) {
        rating.setClient(new Client());
        rating.getClient().setId(clientId);
        var existingRating
                = drugRatingRepository.findByDrugIdAndClientId(rating.getDrug().getId(), clientId);
        if (existingRating.isPresent()) {
            rating.setId(existingRating.get().getId());
        }
        drugRatingRepository.save(rating);
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
