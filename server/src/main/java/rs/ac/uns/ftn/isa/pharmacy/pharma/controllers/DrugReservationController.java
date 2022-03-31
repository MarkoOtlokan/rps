package rs.ac.uns.ftn.isa.pharmacy.pharma.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.pharma.dtos.ProductReservationDto;
import rs.ac.uns.ftn.isa.pharmacy.pharma.mappers.ProductReservationMapper;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.ProductReservationService;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/products/reservations")
public class ProductReservationController {
    private final ProductReservationService productReservationService;

    public ProductReservationController(ProductReservationService productReservationService) {
        this.productReservationService = productReservationService;
    }

    @Secured(Role.PHARMACIST)
    @GetMapping("{reservationId}")
    public ResponseEntity<?> get(@PathVariable long reservationId, HttpServletRequest request){
        try {
            var identity = HttpRequestUtil.getIdentity(request);
            var reservation = productReservationService.getReserved(reservationId,identity.getRoleId());
            return ResponseEntity.ok(ProductReservationMapper.objectToDto(reservation));
        }
        catch (PersistenceException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Secured(Role.PHARMACIST)
    @DeleteMapping("dispense/{reservationId}")
    public ResponseEntity<?> dispense(@PathVariable long reservationId){
        try{
            productReservationService.dispense(reservationId);
            return ResponseEntity.ok().build();
        }
        catch (PersistenceException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    @Secured(Role.PATIENT)
    public List<ProductReservationDto> findClientReservations(HttpServletRequest request) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return productReservationService.findClientReservations(identityProvider.getRoleId()).stream()
                .map(ProductReservationMapper::objectToDto)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @Secured(Role.PATIENT)
    public void reserve(HttpServletRequest request, @RequestBody ProductReservationDto dto){
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        productReservationService.reserve(ProductReservationMapper.dtoToObject(dto), identityProvider.getRoleId());
    }

    @DeleteMapping("{reservationId}")
    @Secured(Role.PATIENT)
    public void cancel(HttpServletRequest request, @PathVariable long reservationId) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        productReservationService.cancelReservation(reservationId, identityProvider.getRoleId());
    }
}
