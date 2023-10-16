package com.example.school.repository;

import com.example.school.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findAllByColor (String color);

    Collection<Faculty> findAllByNameOrColorIgnoreCase (String name, String color);
}
