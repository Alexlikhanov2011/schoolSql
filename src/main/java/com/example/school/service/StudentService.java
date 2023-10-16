package com.example.school.service;

import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student add(Student student) {
        return repository.save(student);
    }

    public Student get(long id) {
        return repository.findById(id).orElse(new Student());
    }

    public Student remove(long id) {
        var entity = repository.findById(id).orElse(null);
        if (entity != null) {
            repository.delete(entity);
        }
        return entity;
    }

    public Student update(Student student) {
        return repository.findById(student.getId())
                .map(entity -> repository.save(student))
                .orElse(null);
    }

    public Collection<Student> filterByAge(int age) {
        return repository.findAllByAge(age);
    }

    public Collection<Student> filterByAgeBetween(int min, int max) {
        return repository.findAllByAgeBetween(min, max);
    }
}
