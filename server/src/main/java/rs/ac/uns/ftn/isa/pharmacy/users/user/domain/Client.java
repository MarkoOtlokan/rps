package rs.ac.uns.ftn.isa.pharmacy.users.user.domain;

import rs.ac.uns.ftn.isa.pharmacy.users.person.domain.Person;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="clients")
@SequenceGenerator(name = "clients_seq", initialValue = 100, allocationSize = 1)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_seq")
    private long id;
    @OneToOne
    private Person person;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
//    private List<Appointment> appointments;
//    @ManyToMany
//    private List<Product> allergicTo;
//    private int penalties;

//    public Boolean canSchedule(Appointment appointment){
//        for(var clientAppointment:appointments)
//            if(appointment.overlaps(clientAppointment.getTerm()))
//                return false;
//        return true;
//    }
//
//    public void addAppointment(Appointment appointment){
//        appointments.add(appointment);
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

//    public List<Appointment> getAppointments() {
//        return appointments;
//    }
//
//    public void setAppointments(List<Appointment> appointment) {
//        this.appointments = appointment;
//    }
//
//    public int getPenalties() {
//        return penalties;
//    }
//
//    public void setPenalties(int penalties) {
//        this.penalties = penalties;
//    }
//
//    public List<Product> getAllergicTo() {
//        return allergicTo;
//    }
//
//    public void setAllergicTo(List<Product> allergicTo) {
//        this.allergicTo = allergicTo;
//    }
//
//    public void penalize() {
//        this.penalties += 1;
//    }
//
//    public boolean isBanned() {
//        return this.penalties >= 3;
//    }
}
