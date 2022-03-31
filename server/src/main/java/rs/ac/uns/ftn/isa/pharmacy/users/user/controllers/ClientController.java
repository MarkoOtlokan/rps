package rs.ac.uns.ftn.isa.pharmacy.users.user.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Drug;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.*;
import rs.ac.uns.ftn.isa.pharmacy.users.user.mappers.ClientInfoMapper;
import rs.ac.uns.ftn.isa.pharmacy.users.user.mappers.PatientBasicInfoMapper;
import rs.ac.uns.ftn.isa.pharmacy.users.user.mappers.PatientInfoMapper;
import rs.ac.uns.ftn.isa.pharmacy.users.user.services.ClientService;
import rs.ac.uns.ftn.isa.pharmacy.users.user.services.PatientService;
import rs.ac.uns.ftn.isa.pharmacy.pharma.services.DrugService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
    public ClientProfilePreviewDto getPatientProfilePreview(@PathVariable long clientId){
        return new ClientProfilePreviewDto(clientService.get(clientId));
    }

    @GetMapping
    @Secured(Role.CLIENT)
    public ClientInfoDto getClientInfo(HttpServletRequest request) {
        IdentityProvider identityProvider = HttpRequestUtil.getIdentity(request);
        return ClientInfoMapper.objectToDto(clientService.get(identityProvider.getRoleId()));
    }
}