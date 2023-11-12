package com.example.school.service;

import com.example.school.model.Faculty;
import com.example.school.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyService {
    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    private final FacultyRepository repository;

    public Faculty add(Faculty faculty) {
        return repository.save(faculty);
    }

    public Faculty get(long id) {
        return repository.findById(id).orElse(new Faculty());
    }

    public Faculty remove(long id) {
        var entity = repository.findById(id).orElse(null);
        if (entity!= null){
            repository.delete(entity);
        }
        return entity;
    }

    public Faculty update(Faculty faculty) {
        return repository.findById(faculty.getId())
                .map(entity-> repository.save(faculty))
                .orElse(null);
    }

    public Collection<Faculty> filterByNameOrColor(String name, String color) {
        return repository.findAllByNameOrColorIgnoreCase(name,color);
    }
}
