
package com.ws.dc.domain.child;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int minAgeMonths;
    private int maxAgeMonths;
    
    private boolean isAgeAllowed(Child child) {
        int ageMonths = child.getAgeInMonths();
        return ageMonths >= minAgeMonths && ageMonths <= maxAgeMonths;
    }

    public Enrollment enrollChild(Child child, ClassRoom classRoom) {
        if (!isAgeAllowed(child)) throw new IllegalArgumentException("Child age not allowed for this program");
        if (classRoom.isFull()) throw new IllegalArgumentException("Class is full");
        
        Enrollment enrollment = new Enrollment(child, classRoom, true);
        child.addEnrollment(enrollment);
        classRoom.addChild(child);
        
        return enrollment;
    }

    public void withdrawEnrollment(Enrollment enrollment) {
        enrollment.withdraw();
    }
}
