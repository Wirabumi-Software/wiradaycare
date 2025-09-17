package com.ws.dc.controller;

import com.ws.dc.domain.child.ClassRoom;
import com.ws.dc.domain.child.ClassRoomRepository;
import com.ws.dc.domain.child.Program;
import com.ws.dc.domain.child.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassRoomController {
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private ProgramRepository programRepository;

    @GetMapping
    public List<ClassRoom> getAllClassRooms() {
        return classRoomRepository.findAll();
    }

    @GetMapping("/{id}")
    public ClassRoom getClassRoom(@PathVariable Long id) {
        return classRoomRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ClassRoom createClassRoom(@RequestBody ClassRoom classRoom) {
        Program program = classRoom.getProgram();
        if (program != null) {
            if (program.getId() != null) {
                // Resolve existing program
                Program existingProgram = programRepository.findById(program.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Program not found"));
                classRoom.setProgram(existingProgram);
            } else {
                // Create new program
                Program savedProgram = programRepository.save(program);
                classRoom.setProgram(savedProgram);
            }
        }
        return classRoomRepository.save(classRoom);
    }

    @PutMapping("/{id}")
    public ClassRoom updateClassRoom(@PathVariable Long id, @RequestBody ClassRoom classRoom) {
        classRoom.setId(id);
        return classRoomRepository.save(classRoom);
    }

    @DeleteMapping("/{id}")
    public void deleteClassRoom(@PathVariable Long id) {
        classRoomRepository.deleteById(id);
    }
}
