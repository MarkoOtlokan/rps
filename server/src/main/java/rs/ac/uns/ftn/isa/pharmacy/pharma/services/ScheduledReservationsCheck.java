package rs.ac.uns.ftn.isa.pharmacy.pharma.services;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.users.user.services.ClientService;

@Component
public class ScheduledReservationsCheck {
    private final ProductReservationService productReservationService;
    private final ClientService clientService;

    public ScheduledReservationsCheck(ProductReservationService productReservationService, ClientService clientService) {
        this.productReservationService = productReservationService;
        this.clientService = clientService;
    }

}
