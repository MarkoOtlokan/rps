package rs.ac.uns.ftn.isa.pharmacy.users.user.controllers;


import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.*;
import rs.ac.uns.ftn.isa.pharmacy.users.user.mappers.ClientInfoMapper;
import rs.ac.uns.ftn.isa.pharmacy.users.user.services.ClientService;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.DrugService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    private final ClientService clientService;
    private final DrugService drugService;

    public ClientController(ClientService clientService, DrugService drugService) {
        this.clientService = clientService;
        this.drugService = drugService;
    }

    @GetMapping("/profile-preview/{clientId}")
    public ClientProfilePreviewDto getClientProfilePreview(@PathVariable long clientId){
        return new ClientProfilePreviewDto(clientService.get(clientId));
    }

    @GetMapping
    @Secured(Role.CLIENT)
    public ClientInfoDto getClientInfo(HttpServletRequest request) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return ClientInfoMapper.objectToDto(clientService.get(identityProvider.getRoleId()));
    }
}
