package rs.ac.uns.ftn.isa.pharmacy.users.user.dtos;

import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Client;
import rs.ac.uns.ftn.isa.pharmacy.users.user.domain.Gender;

import java.time.LocalDate;

public class ClientProfilePreviewDto {
    private String pid;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;
    private String username;
    private String email;

    public ClientProfilePreviewDto(Client client) {
        this.pid = client.getPerson().getPid();
        this.firstName = client.getPerson().getFirstName();
        this.lastName = client.getPerson().getLastName();
        this.gender = client.getPerson().getGender() == Gender.MALE ? "Male" : "Female";
        this.phoneNumber = client.getPerson().getPhoneNumber();
        this.dateOfBirth = client.getPerson().getDateOfBirth();
        this.address = client.getPerson().getAddress().toString();
        this.username = client.getPerson().getCredentials().getUsername();
        this.email = client.getPerson().getCredentials().getEmail();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
