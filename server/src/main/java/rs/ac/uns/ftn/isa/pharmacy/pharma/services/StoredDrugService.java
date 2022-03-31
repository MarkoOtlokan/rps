package rs.ac.uns.ftn.isa.pharmacy.pharma.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.mail.dtos.ProductNonExistentMailInfo;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.ProductNotAvailableException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.ProductRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.StoredProductRepository;
import rs.ac.uns.ftn.isa.pharmacy.mail.services.EmailService;

@Service
public class StoredProductService {
    private final StoredProductRepository storedProductRepository;
    private final PharmacyRepository pharmacyRepository;
    private final ProductRepository productRepository;
    private final EmailService emailService;

    public StoredProductService(StoredProductRepository storedProductRepository, PharmacyRepository pharmacyRepository,
                             ProductRepository productRepository, EmailService emailService) {
        this.storedProductRepository = storedProductRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.productRepository = productRepository;
        this.emailService = emailService;
    }

    public void checkAvailability(long pharmacyId,long productId) throws ProductNotAvailableException{
        validateProductExisting(pharmacyId, productId);
        validateProductAvailable(pharmacyId, productId);
    }

    private void validateProductAvailable(long pharmacyId, long productId) throws ProductNotAvailableException {
        if(!isAvailable(pharmacyId, productId)){
            var product= storedProductRepository.getOneFromPharmacy(pharmacyId, productId);
            emailService.sendInsufficientProductQuantityMessage(product);
            throw new ProductNotAvailableException();
        }
    }

    private void validateProductExisting(long pharmacyId, long productId) throws ProductNotAvailableException{
        if(!existsInPharmacy(pharmacyId, productId)){
            emailService.sendProductNonExistentMessage(new ProductNonExistentMailInfo(productRepository.getOne(productId),
                    pharmacyRepository.getPharmacyAdmin(pharmacyId)));
            throw new ProductNotAvailableException();
        }
    }

    private boolean existsInPharmacy(long pharmacyId, long productId) {
        return this.storedProductRepository.getOneFromPharmacy(pharmacyId, productId) != null;
    }

    private boolean isAvailable(long pharmacyId, long productId){
        return this.storedProductRepository.quantityInPharmacy(pharmacyId,productId) > 0;
    }
}
