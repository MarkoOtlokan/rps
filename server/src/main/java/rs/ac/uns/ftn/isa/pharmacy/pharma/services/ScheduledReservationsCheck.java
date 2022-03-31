package rs.ac.uns.ftn.isa.pharmacy.pharma.services;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isa.pharmacy.users.user.services.ClientService;

@Component
public class ScheduledReservationsCheck {
    private final DrugReservationService drugReservationService;
    private final ClientService clientService;

    public ScheduledReservationsCheck(DrugReservationService drugReservationService, ClientService clientService) {
        this.drugReservationService = drugReservationService;
        this.clientService = clientService;
    }

}
