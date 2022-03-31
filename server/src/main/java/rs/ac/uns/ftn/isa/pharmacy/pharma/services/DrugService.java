package rs.ac.uns.ftn.isa.pharmacy.pharma.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredProduct;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.ProductCreationDto;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityExistsException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.MessageException;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.ProductRepository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.StoredProductRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StoredProductRepository storedProductRepository;

    public ProductService(ProductRepository productRepository,
                       StoredProductRepository storedProductRepository
    ) {
        this.productRepository = productRepository;
        this.storedProductRepository = storedProductRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class.getSimpleName(), id));
    }

    public Product create(ProductCreationDto dto) throws MessageException {
        if (dto.getProduct() == null) throw new InvalidEntityException("Product");
        dto.getProduct().validate();

        if (productRepository.existsById(dto.getProduct().getId()))
            throw new EntityExistsException(dto.getProduct().getName());

        if (dto.getProduct().getAlternatives() == null)
            dto.getProduct().setAlternatives(new LinkedList<>());

        for (var altId : dto.getAlternativeProductIds()) {
            Optional<Product> optionalProduct = productRepository.findById(altId.longValue());
            if (optionalProduct.isEmpty())
                throw new MessageException("Alternative mentioned is nonexistent [" + altId + "].");
            dto.getProduct().getAlternatives().add(optionalProduct.get());
        }

        return productRepository.save(dto.getProduct());
    }

    public Product update(Product newProduct, Long id) {
        if (productRepository.existsById(id)) {
            newProduct.setId(id);
            return productRepository.save(newProduct);
        }
        throw new EntityNotFoundException("Product", newProduct.getId());
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<StoredProduct> searchByName(String name) {
        return storedProductRepository.searchByName(name);
    }

    public List<Product> getAlternatives(long productId) {
        return productRepository.getOne(productId).getAlternatives();
    }
}
