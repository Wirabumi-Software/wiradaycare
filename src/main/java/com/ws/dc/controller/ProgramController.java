package com.ws.dc.controller;

import com.ws.dc.domain.child.Program;
import com.ws.dc.domain.child.ProgramRepository;
import com.ws.dc.domain.child.Child;
import com.ws.dc.domain.child.ChildRepository;
import com.ws.dc.domain.child.ClassRoom;
import com.ws.dc.domain.child.ClassRoomRepository;
import com.ws.dc.domain.child.Enrollment;
import com.ws.dc.domain.child.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/programs")
public class ProgramController {
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    @GetMapping
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    @GetMapping("/{id}")
    public Program getProgram(@PathVariable Long id) {
        return programRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Program createProgram(@RequestBody Program program) {
        return programRepository.save(program);
    }

    @PutMapping("/{id}")
    public Program updateProgram(@PathVariable Long id, @RequestBody Program program) {
        program.setId(id);
        return programRepository.save(program);
    }

    @DeleteMapping("/{id}")
    public void deleteProgram(@PathVariable Long id) {
        programRepository.deleteById(id);
    }

    // Domain action: enroll a child in a program/classroom
     @PostMapping("/{programId}/enroll")
    public Enrollment enrollChild(
            @PathVariable Long programId,
            @RequestParam Long childId,
            @RequestParam Long classRoomId) {
        
        Program program = programRepository.findById(programId).orElseThrow(() -> new IllegalArgumentException("Program not found"));
        Child child = childRepository.findById(childId).orElseThrow(() -> new IllegalArgumentException("Child not found"));
        ClassRoom classRoom = classRoomRepository.findById(classRoomId).orElseThrow(() -> new IllegalArgumentException("ClassRoom not found"));
        Enrollment enrollment = program.enrollChild(child, classRoom);
        enrollmentRepository.save(enrollment);
        programRepository.save(program);
        childRepository.save(child);
        classRoomRepository.save(classRoom);
        return enrollment; 
    }

    @PostMapping("/{programId}/withdraw")
    public Enrollment withdrawEnrollment(
            @PathVariable Long programId,
            @RequestParam Long enrollmentId) {
        Program program = programRepository.findById(programId).orElseThrow(() -> new IllegalArgumentException("Program not found"));
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
        program.withdrawEnrollment(enrollment);
        enrollmentRepository.save(enrollment);
        programRepository.save(program);
        return enrollment;
    }
}
