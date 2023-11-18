package com.example.school.controller;

import com.example.school.model.Faculty;
import com.example.school.model.Student;
import com.example.school.service.FacultyService;
import com.example.school.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;
    private final FacultyService facultyService;

    public StudentController(StudentService service, FacultyService facultyService) {
        this.service = service;
        this.facultyService = facultyService;
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.add(student);
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable long id) {
        return service.get(id);
    }

    @PutMapping
    public Student update(@RequestBody Student student) {
        return service.update(student);
    }

    @DeleteMapping("/{id}")
    public Student remove(@PathVariable long id) {
        return service.remove(id);
    }

    @GetMapping("/{studentId}/faculty")
    public Faculty facultyByStudent(@PathVariable long studentId) {
        return service.get(studentId).getFaculty();
    }

    @GetMapping("/byAge")
    public Collection<Student> byAge(@RequestParam int age) {
        return service.filterByAge(age);
    }

    @GetMapping("/byAgeBetween")
    public Collection<Student> byAgeBetween(@RequestParam int min, @RequestParam int max) {
        return service.filterByAgeBetween(min, max);
    }
    @GetMapping("/findAllStudentsInSchool")
    public Integer findAllStudentsInSchool() {
        return service.findAllStudentsInSchool();
    }

    @GetMapping("/AvgAgeStudent")
    public Double AvgAgeStudent() {
        return service.AvgAgeStudent();
    }

    @GetMapping("/findLastFiveStudent")
    public List<Student> findLastFiveStudents() {
        return service.findLastFiveStudents();
    }

@GetMapping ("/nameStratsA")
    public Collection<String> getStudentsNameStartsA(){
        return service.getStudentNameStartA();
}
@GetMapping ("/averageAge")
    public double getStreamaverageAge(){
        return service.streamAverageAge();
}
}
