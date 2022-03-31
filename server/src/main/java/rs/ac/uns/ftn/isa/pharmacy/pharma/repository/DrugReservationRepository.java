package rs.ac.uns.ftn.isa.pharmacy.pharma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.ProductReservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductReservationRepository extends JpaRepository<ProductReservation, Long> {
    List<ProductReservation> findAllByClientId(long clientId);

    @Query("select dr from ProductReservation dr where dr.pickUpBefore < :now")
    List<ProductReservation> findExpired(@Param("now") LocalDate now);
}
