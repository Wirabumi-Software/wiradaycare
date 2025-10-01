package com.ws.dc.controller;

import com.ws.dc.domain.child.Child;
import com.ws.dc.domain.child.ChildRepository;
import com.ws.dc.domain.child.Enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/children")
public class ChildController {
    @Autowired
    private ChildRepository childRepository;

    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    @GetMapping
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    @GetMapping("/{id}")
    public Child getChild(@PathVariable Long id) {
        return childRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Child createChild(@RequestBody Child child) {
        return childRepository.save(child);
    }

    @PutMapping("/{id}")
    public Child updateChild(@PathVariable Long id, @RequestBody Child child) {
        child.setId(id);
        return childRepository.save(child);
    }

    @DeleteMapping("/{id}")
    public void deleteChild(@PathVariable Long id) {
        childRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    @GetMapping("/{id}/active-enrollment")
    public Enrollment getActiveEnrollment(@PathVariable Long id) {
        Child child = childRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Child not found"));
        return child.getActiveEnrollment();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    @GetMapping("/{id}/enrollments")
    public List<Enrollment> getEnrollmentHistory(@PathVariable Long id) {
        Child child = childRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Child not found"));
        return child.getEnrollments();
    }

}
