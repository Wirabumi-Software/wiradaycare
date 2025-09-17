
package com.ws.dc.domain.child;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Transient
    private java.util.List<Child> children = new java.util.ArrayList<>();

    public boolean isFull() {
        return children.size() >= capacity;
    }

    void addChild(Child child) {
        if (isFull()) throw new IllegalStateException("Class is full");
        children.add(child);
    }
}
