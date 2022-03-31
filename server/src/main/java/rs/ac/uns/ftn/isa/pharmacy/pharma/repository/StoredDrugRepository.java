package rs.ac.uns.ftn.isa.pharmacy.pharma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.StoredProduct;

import java.util.List;

@Repository
public interface StoredProductRepository extends JpaRepository<StoredProduct, Long> {
    @Query("select d from StoredProduct d where lower(d.product.name) like lower(concat('%', :name, '%'))")
    List<StoredProduct> searchByName(@Param("name") String name);

    @Query("select d.quantity from StoredProduct d where d.pharmacy.id=:pharmacyId and d.product.id=:productId")
    Integer quantityInPharmacy(@Param("pharmacyId") Long pharmacyId, @Param("productId") Long productId);

    @Query("select d from StoredProduct d where d.pharmacy.id=:pharmacyId and d.product.id=:productId")
    StoredProduct getOneFromPharmacy(@Param("pharmacyId") Long pharmacyId, @Param("productId") Long productId);
}
