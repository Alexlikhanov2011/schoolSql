package com.example.school.controller;

import com.example.school.model.Faculty;
import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
    }

    @Test
    void contextLoads() throws Exception{
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testGetFacultyById() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty" + "/by-id", String.class))
                .isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(5L);
        faculty.setName("Пуффендуй");
        faculty.setColor("Желтый");

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyByColor() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty" + "/by-color", String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyByNameOrColor() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty" + "/by-name-or-color", String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentsByFacultyId() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty" + "/students-by-faculty-id", String.class))
                .isNotNull();
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(5L);
        faculty.setName("Когтевран");
        faculty.setColor("Синий");

        ResponseEntity<Void> resp = restTemplate.exchange("http://localhost:" + port + "/faculty", HttpMethod.DELETE, null, Void.class);
    }

    @Test
    public void testPutStudent() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(5L);
        faculty.setName("Слизерин");
        faculty.setColor("Зеленый");

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }
}
