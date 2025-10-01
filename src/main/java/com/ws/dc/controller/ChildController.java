package com.ws.dc.controller;

import com.ws.dc.domain.child.Child;
import com.ws.dc.domain.child.ChildRepository;
import com.ws.dc.domain.child.Enrollment;
import com.ws.dc.domain.child.EnrollmentResult;
import com.ws.dc.domain.child.Program;
import com.ws.dc.domain.child.ProgramRepository;
import com.ws.dc.domain.child.ClassRoom;
import com.ws.dc.domain.child.ClassRoomRepository;
import com.ws.dc.domain.child.EnrollmentRepository;

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
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

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

    @PostMapping("/{childId}/enroll")
    public Enrollment enrollChild(
            @PathVariable Long childId,
            @RequestParam Long programId,
            @RequestParam Long classRoomId) {
        
        Child child = childRepository.findById(childId).orElseThrow(() -> new IllegalArgumentException("Child not found"));
        Program program = programRepository.findById(programId).orElseThrow(() -> new IllegalArgumentException("Program not found"));
        ClassRoom classRoom = classRoomRepository.findById(classRoomId).orElseThrow(() -> new IllegalArgumentException("ClassRoom not found"));
        
        EnrollmentResult result = child.enrollInProgram(program, classRoom);
        
        saveToDB(result);
        
        return result.getEnrollment();
    }

    private void saveToDB(EnrollmentResult result) {
        enrollmentRepository.save(result.getEnrollment());
        childRepository.save(result.getChild());
        classRoomRepository.save(result.getClassRoom());
    }

    @PostMapping("/{childId}/withdraw")
    public Child withdrawChild(@PathVariable Long childId) {
        Child child = childRepository.findById(childId).orElseThrow(() -> new IllegalArgumentException("Child not found"));
        
        EnrollmentResult result = child.withdrawFromCurrentEnrollment();
        
        saveToDB(result);
        
        return result.getChild();
    }

}
