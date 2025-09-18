
package com.ws.dc.domain.child;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;

    private String medicalHistory;
    private String allergies;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private com.ws.dc.domain.user.User parent;

    @OneToMany(mappedBy = "child")
    @JsonManagedReference("child-enrollments")
    private java.util.List<Enrollment> enrollments;

    public int getAgeInMonths() {
        if (dateOfBirth == null) return 0;
        LocalDate now = LocalDate.now();
        return (now.getYear() - dateOfBirth.getYear()) * 12 + (now.getMonthValue() - dateOfBirth.getMonthValue());
    }

    void addEnrollment(Enrollment enrollment) {
        if (enrollments == null) enrollments = new java.util.ArrayList<>();
        
        if (getActiveEnrollment() != null) throw new IllegalStateException("Child already has an active enrollment");
            enrollments.add(enrollment);
    }

    @JsonIgnore
    public Enrollment getActiveEnrollment() {
        if (enrollments == null) return null;
        return enrollments.stream().filter(Enrollment::isActive).findFirst().orElse(null);
    }
}
