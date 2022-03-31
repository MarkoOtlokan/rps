package rs.ac.uns.ftn.isa.pharmacy.pharma.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.pharmacy.pharma.exceptions.ProductNotAvailableException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.ProductService;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.StoredProductService;

@RestController
@RequestMapping("api/stored-products")
public class StoredProductController {
    private final StoredProductService storedProductService;
    private final ProductService productService;

    public StoredProductController(StoredProductService storedProductService, ProductService productService) {
        this.storedProductService = storedProductService;
        this.productService = productService;
    }

    @GetMapping("/is-available")
    public ResponseEntity<?> checkAvailability(@RequestParam long pharmacyId, @RequestParam long productId){
        try {
            storedProductService.checkAvailability(pharmacyId,productId);
        }
        catch (ProductNotAvailableException e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(productService.getAlternatives(productId));
        }
        return ResponseEntity.ok().body(null);
    }
}
