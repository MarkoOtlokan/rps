package rs.ac.uns.ftn.isa.pharmacy.pharma.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.*;
import rs.ac.uns.ftn.isa.pharmacy.pharma.mappers.ProductSearchMapper;
import rs.ac.uns.ftn.isa.pharmacy.pharma.mappers.ProductSimpleMapper;
import rs.ac.uns.ftn.isa.pharmacy.pharma.mappers.StoredProductMapper;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.ProductService;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.MessageException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("{id}")
    public Product get(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("simple")
    public ResponseEntity<List<ProductSimpleDto>> getAllSimple() {
        return ResponseEntity.ok(productService.findAll().stream().map(ProductSimpleMapper::objectToDto).collect(Collectors.toList()));
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity<?> search(HttpServletRequest request, @PathVariable String productName) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        // This will block Role.Supplier from accessing this. Ugly but works.
        if (
            identityProvider != null
            && !identityProvider.getAuthorities().isEmpty()
            && ((SimpleGrantedAuthority)identityProvider.getAuthorities().toArray()[0]).getAuthority().equals(Role.SUPPLIER)
        ){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<StoredProductDto> searchResult = productService.searchByName(productName).stream()
                                                        .map(StoredProductMapper::objectToDto)
                                                        .collect(Collectors.toList());
        return ResponseEntity.ok(searchResult);
    }

    @GetMapping("/client-search/{productName}")
    public List<ProductSearchDto> clientSearch(@PathVariable String productName) {
        return productService.searchByName(productName).stream()
                .map(ProductSearchMapper::objectToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Secured(Role.SYS_ADMIN)
    public ResponseEntity<?> create(@RequestBody ProductCreationDto dto) {
        try {
            productService.create(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public Product update(@RequestBody Product product, @PathVariable Long id) {
        return productService.update(product, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }


}
