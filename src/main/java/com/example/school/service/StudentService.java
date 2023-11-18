package com.example.school.service;

import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository repository) {
        this.studentRepository = repository;
    }

    public Student add(Student student) {
        logger.info("Add method was invoked");
        return studentRepository.save(student);
    }

    public Student get(long id) {
        logger.info("get method was invoked with argument {}", id);
        return studentRepository.findById(id).orElse(new Student());
    }

    public Student remove(long id) {
        logger.info("remove method was invoked with argument {}", id);
        var entity = studentRepository.findById(id).orElse(null);
        if (entity != null) {
            studentRepository.delete(entity);
        }
        return entity;
    }

    public Student update(Student student) {
        logger.info("update was invoked");
        return studentRepository.findById(student.getId())
                .map(entity -> studentRepository.save(student))
                .orElse(null);
    }

    public Collection<Student> filterByAge(int age) {
    logger.info("filterAge was invoked with argument {}", age);
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> filterByAgeBetween(int min, int max) {
        logger.info("filterAgeBetween was invoked with argument {}:{}", min,max);
        return studentRepository.findAllByAgeBetween(min, max);
    }
    public Integer findAllStudentsInSchool() {
        logger.error("studentCount was invoked");
        return studentRepository.findAllStudentsInSchool();
    }

    public double AvgAgeStudent() {
        logger.info("averageAge was invoked");
        return studentRepository.AvgAgeStudent();
    }

    public List<Student> findLastFiveStudents() {
        return studentRepository.findLastFiveStudents();
    }

    public Collection <String> getStudentNameStartA(){
       return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }
    public double streamAverageAge(){
        return studentRepository.findAll()
                .stream()
                .map(Student::getAge)
                .mapToInt(o->o)
                .average()
                .orElse(0d);
    }
}
