package rs.ac.uns.ftn.isa.pharmacy.supply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.OrderedProduct;

import java.util.List;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {

    @Query("select od from OrderedProduct od where od.order.id = :orderedProductId")
    List<OrderedProduct> getByPurchaseOrderId(@Param("orderedProductId") long purchaseOrderId);
}
