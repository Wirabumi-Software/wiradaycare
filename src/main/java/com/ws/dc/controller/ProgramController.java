package com.ws.dc.controller;

import com.ws.dc.domain.child.Program;
import com.ws.dc.domain.child.ProgramRepository;

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


}
