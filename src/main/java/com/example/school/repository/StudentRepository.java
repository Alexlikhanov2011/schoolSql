package com.example.school.repository;

import com.example.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAge (int age);

    Collection<Student> findAllByAgeBetween (int min, int max);

    @Query(value = "SELECT COUNT(*) FROM student",nativeQuery = true)
    Integer findAllStudentsInSchool();

    @Query(value = "SELECT  AVG(age) as age FROM student", nativeQuery = true)
    double AvgAgeStudent();
    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> findLastFiveStudents();
}