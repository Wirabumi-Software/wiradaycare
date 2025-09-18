
package com.ws.dc.domain.child;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private boolean active;

    void activate() { this.active = true; }
    void withdraw() { this.active = false; }
    public boolean isActive() { return active; }

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
