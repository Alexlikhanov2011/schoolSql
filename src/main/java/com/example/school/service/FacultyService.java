package com.example.school.service;

import com.example.school.model.Faculty;
import com.example.school.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyService {
    private final static Logger logger = LoggerFactory.getLogger(StudentService.class);
    public FacultyService(FacultyRepository repository) {
        this.facultyRepository = repository;
    }

    private final FacultyRepository facultyRepository;

    public Faculty add(Faculty faculty) {
        logger.info("Add method was invoked");
        return facultyRepository.save(faculty);
    }

    public Faculty get(long id) {
        logger.info("get method was invoked with argument {}", id);
        return facultyRepository.findById(id).orElse(new Faculty());
    }

    public Faculty remove(long id) {
        logger.info("remove method was invoked with argument {}", id);
        var entity = facultyRepository.findById(id).orElse(null);
        if (entity!= null){
            facultyRepository.delete(entity);
        }
        return entity;
    }

    public Faculty update(Faculty faculty) {
        logger.info("update was invoked");
        return facultyRepository.findById(faculty.getId())
                .map(entity-> facultyRepository.save(faculty))
                .orElse(null);
    }

    public Collection<Faculty> filterByNameOrColor(String name, String color) {
        logger.info("filterAgeBetween was invoked with argument {}:{}", name,color);
        return facultyRepository.findAllByNameOrColorIgnoreCase(name,color);
    }
}
