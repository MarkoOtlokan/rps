package rs.ac.uns.ftn.isa.pharmacy.users.user.mappers;

import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Client;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.ClientInfoDto;

public class ClientInfoMapper {
    public static ClientInfoDto objectToDto(Client client) {
        return new ClientInfoDto(
                client.getId()
        );
    }
}
