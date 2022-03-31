package rs.ac.uns.ftn.isa.pharmacy.mail.dtos;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Product;
import rs.ac.uns.ftn.isa.pharmacy.users.admin.domain.Admin;

public class ProductNonExistentMailInfo {
    private String productName;
    private String adminMail;
    private String adminFullName;

    public ProductNonExistentMailInfo(Product product, Admin admin){
        this.productName = product.getName();
        this.adminMail = admin.getPerson().getCredentials().getEmail();
        this.adminFullName = admin.getPerson().getFirstName() + " " + admin.getPerson().getLastName();
    }

    public String getProductName() {
        return productName;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public String getAdminFullName() {
        return adminFullName;
    }
}
