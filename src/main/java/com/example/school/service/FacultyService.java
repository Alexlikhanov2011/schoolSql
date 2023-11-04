package com.example.school.service;

import com.example.school.model.Faculty;
import com.example.school.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyService {
    public FacultyService(FacultyRepository repository) {
        this.facultyRepository = repository;
    }

    private final FacultyRepository facultyRepository;

    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty get(long id) {
        return facultyRepository.findById(id).orElse(new Faculty());
    }

    public Faculty remove(long id) {
        var entity = facultyRepository.findById(id).orElse(null);
        if (entity!= null){
            facultyRepository.delete(entity);
        }
        return entity;
    }

    public Faculty update(Faculty faculty) {
        return facultyRepository.findById(faculty.getId())
                .map(entity-> facultyRepository.save(faculty))
                .orElse(null);
    }

    public Collection<Faculty> filterByNameOrColor(String name, String color) {
        return facultyRepository.findAllByNameOrColorIgnoreCase(name,color);
    }
}
