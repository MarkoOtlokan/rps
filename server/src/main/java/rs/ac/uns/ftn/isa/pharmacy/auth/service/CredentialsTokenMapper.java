package rs.ac.uns.ftn.isa.pharmacy.auth.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.AuthToken;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Client;
import rs.ac.uns.ftn.isa.pharmacy.pharma.repository.PharmacyRepository;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.services.EmployeeService;
import rs.ac.uns.ftn.isa.pharmacy.users.user.services.ClientService;


@Service
public class CredentialsTokenMapper {
    private final EmployeeService employeeService;
    private final PharmacyRepository pharmacyRepository;
    private final ClientService clientService;

    public CredentialsTokenMapper(
            EmployeeService employeeService,
            ClientService clientService,
            PharmacyRepository pharmacyRepository
    ){
        this.employeeService = employeeService;
        this.pharmacyRepository = pharmacyRepository;
        this.clientService = clientService;
    }

    public AuthToken createAuthToken(Credentials credentials) {
        AuthToken token = new AuthToken();
        token.setEmail(credentials.getEmail());
        token.setPersonId(credentials.getPerson().getId());
        token.setRole(credentials.getRole());
        AttachRoleId(token, credentials);
        return token;
    }

    private void AttachRoleId(AuthToken token, Credentials credentials) {
        switch (credentials.getRole()) {
            case Role.CLIENT:
                Client client = clientService.findByPersonId(credentials.getPerson().getId());
                token.setRoleId(client.getId());
                break;
            case Role.DERMATOLOGIST:
            case Role.PHARMACIST:
                Employee employee = employeeService.findByPersonId(credentials.getPerson().getId());
                token.setRoleId(employee.getId());
                break;
            case Role.PH_ADMIN:
                Pharmacy pharmacy = pharmacyRepository.getByAdmin(credentials.getPerson().getId());
                token.setRoleId(pharmacy.getId());
                break;
            case Role.SUPPLIER:
                token.setRoleId(credentials.getPerson().getId());
        }
    }

}
