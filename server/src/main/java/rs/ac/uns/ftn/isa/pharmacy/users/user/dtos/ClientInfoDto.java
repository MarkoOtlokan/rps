package rs.ac.uns.ftn.isa.pharmacy.users.user.dtos;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;

import java.util.List;

public class ClientInfoDto {
    private long id;


    public ClientInfoDto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
