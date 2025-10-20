package br.com.LucasNog21.School.controller;

import br.com.LucasNog21.School.controller.docs.CourseControllerDocs;
import br.com.LucasNog21.School.model.Course;
import br.com.LucasNog21.School.service.CourseServices;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cursos")
@Tag(name="Cursos", description = "Endpoints para gestão de cursos")
public class CourseController implements CourseControllerDocs {

    @Autowired
    private CourseServices service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)

    @Override
    public List<Course> findAll(@RequestParam(value="nome", required = false) String name) {
        if (name != null) {
            return service.findByNameContaining(name);
        }
        return service.findAll();
    }

    @GetMapping(value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Course findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Course create(@RequestBody Course course) {
        return service.create(course);
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Course update(@PathVariable("id") Long id ,@RequestBody Course course) {
        return service.update(id, course);
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}