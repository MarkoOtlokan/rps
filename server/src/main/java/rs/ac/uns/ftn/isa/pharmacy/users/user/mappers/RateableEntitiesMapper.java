package rs.ac.uns.ftn.isa.pharmacy.users.user.mappers;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;
import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.users.employee.domain.Employee;
import rs.ac.uns.ftn.isa.pharmacy.users.user.dtos.RateableEntitiesDto;

import java.util.List;
import java.util.stream.Collectors;

public class RateableEntitiesMapper {
    public static RateableEntitiesDto objectsToDto(List<Product> products,
                                                   List<Pharmacy> pharmacies) {

        var productDtos = products.stream()
                .map(RatingProductMapper::objectToDto).collect(Collectors.toList());
        var pharmacyDtos = pharmacies.stream()
                .map(RatingPharmacyMapper::objectToDto).collect(Collectors.toList());
        return new RateableEntitiesDto(pharmacyDtos, productDtos);
    }
}
