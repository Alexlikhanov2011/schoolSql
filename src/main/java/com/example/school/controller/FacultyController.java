package com.example.school.controller;

import com.example.school.model.Faculty;
import com.example.school.model.Student;
import com.example.school.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return service.add(faculty);
    }

    @GetMapping("/{id}")
    public Faculty get(@PathVariable long id) {
        return service.get(id);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return service.update(faculty);
    }

    @DeleteMapping("/{id}")
    public Faculty remove(@PathVariable long id) {
        return service.remove(id);
    }

    @GetMapping("/{facultyId}/students")
    public Collection<Student> findByFaculty(@PathVariable long facultyId) {
        return service.get(facultyId).getStudents();
    }

    @GetMapping("/byNameOrColor")
    public Collection<Faculty> byNameOrColor(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color
    ) {
        return service.filterByNameOrColor(name, color);
    }
}
