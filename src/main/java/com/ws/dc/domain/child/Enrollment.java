
package com.ws.dc.domain.child;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "child_id")
    @ManyToOne
    @JsonBackReference("child-enrollments")
    private Child child;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;
    private LocalDate enrollmentDate;
    
    @Setter(lombok.AccessLevel.NONE) // Prevent Lombok from generating setActive()
    private boolean active;

    void activate() { this.active = true; }
    void withdraw() { this.active = false; }
    boolean isActive() { return active; }

    public static Enrollment createEnrollment(Child child, Program program, ClassRoom classRoom) {
        
        validateAge(child, program);
        validateClassRoomCapasity(classRoom);
        validateNoDuplicatedActiveEnrollment(child);
        
        return new Enrollment(child, classRoom, true);
    }

    private static void validateNoDuplicatedActiveEnrollment(Child child) {
        if (child.getActiveEnrollment() != null) {
            throw new IllegalStateException("Child already has an active enrollment");
        }
    }

    private static void validateClassRoomCapasity(ClassRoom classRoom) {
        if (classRoom.isFull()) {
            throw new IllegalArgumentException("Class is full");
        }
    
    }
    private static void validateAge(Child child, Program program) {
        int ageMonths = child.getAgeInMonths();
        if (ageMonths < program.getMinAgeMonths() || ageMonths > program.getMaxAgeMonths()) {
            throw new IllegalArgumentException("Child age not allowed for this program");
        }
    }

    // Constructor for domain logic
    Enrollment(Child child, ClassRoom classRoom, boolean active) {
        this.child = child;
        this.classRoom = classRoom;
        this.enrollmentDate = LocalDate.now();
        this.active = active;
    }

    // Default constructor for JPA
    public Enrollment() {}

}
