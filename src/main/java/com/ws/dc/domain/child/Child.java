
package com.ws.dc.domain.child;
import jakarta.persistence.*;
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

    @JsonIgnore
    public Enrollment getActiveEnrollment() {
        if (enrollments == null) return null;
        return enrollments.stream().filter(Enrollment::isActive).findFirst().orElse(null);
    }

    public EnrollmentResult enrollInProgram(Program program, ClassRoom classRoom) {
        Enrollment enrollment = Enrollment.createEnrollment(this, program, classRoom);
        
        if (enrollments == null) enrollments = new java.util.ArrayList<>();
        enrollments.add(enrollment);
        classRoom.addChild(this);
        
        // Return all affected entities that need persistence
        return new EnrollmentResult(enrollment, this, classRoom);
    }

    public EnrollmentResult withdrawFromCurrentEnrollment() {
        Enrollment activeEnrollment = getActiveEnrollment();
        if (activeEnrollment == null) {
            throw new IllegalStateException("Child has no active enrollment to withdraw from");
        }
        
        ClassRoom classRoom = activeEnrollment.getClassRoom();
        activeEnrollment.withdraw();
        classRoom.removeChild(this);
        
        return new EnrollmentResult(activeEnrollment, this, classRoom);
    }
}
