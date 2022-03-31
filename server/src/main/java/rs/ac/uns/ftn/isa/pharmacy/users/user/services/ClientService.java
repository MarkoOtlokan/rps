package rs.ac.uns.ftn.isa.pharmacy.users.user.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.RegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.service.RegistrationService;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityExistsException;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.InvalidEntityException;
import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Client;
import rs.ac.uns.ftn.isa.pharmacy.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.isa.pharmacy.users.user.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final RegistrationService registrationService;

    public ClientService(ClientRepository clientRepository, RegistrationService registrationService) {
        this.clientRepository = clientRepository;
        this.registrationService = registrationService;
    }


    public Client get(long clientId){
        return clientRepository.getOne(clientId);
    }

    public Client update(Client client, long id) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            return clientRepository.save(client);
        }
        throw new EntityNotFoundException(Client.class.getSimpleName(), id);
    }


    public Client findByPersonId(long id) {
        return clientRepository.findByPersonId(id);
    }

    public void register(RegistrationDto dto)
            throws EntityExistsException, rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.EntityNotFoundException,
            InvalidEntityException
    {
        Person person = registrationService.registerClient(dto);
        Client client = new Client();
        client.setPerson(person);
        clientRepository.save(client);
    }
}
