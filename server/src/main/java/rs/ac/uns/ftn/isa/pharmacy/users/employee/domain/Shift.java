package rs.ac.uns.ftn.isa.pharmacy.users.employee.domain;

import rs.ac.uns.ftn.isa.pharmacy.pharma.domain.Pharmacy;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shifts")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDateTime start;
    private LocalDateTime end;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="pharmacy_id")
    private Pharmacy pharmacy;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="employee_id")
    private Employee employee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    /**
     * Get shift as a {@link Term} object that represents the beginning, duration and the end of shift.
     * @return {@link Term} object.
     */
    public Term asTerm() {
        return new Term(start, Duration.between(start, end));
    }
}
