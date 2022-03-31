package rs.ac.uns.ftn.isa.pharmacy.users.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.ProductRating;

import java.util.Optional;

@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {
    Optional<ProductRating> findByProductIdAndClientId(long productId, long clientId);
}
