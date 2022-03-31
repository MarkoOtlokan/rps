package rs.ac.uns.ftn.isa.pharmacy.users.user.mappers;

import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Client;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.ClientBasicInfoDto;

public class ClientBasicInfoMapper {
    public static ClientBasicInfoDto objectToDto(Client client){
        return new ClientBasicInfoDto(
                client.getId(),
                client.getPerson().getPid(),
                client.getPerson().getFirstName(),
                client.getPerson().getLastName()
        );
    }
}
