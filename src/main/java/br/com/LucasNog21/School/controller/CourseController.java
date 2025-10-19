package br.com.LucasNog21.School.controller;

import br.com.LucasNog21.School.model.Course;
import br.com.LucasNog21.School.service.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseServices service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> findAll(@RequestParam(value="nome", required = false) String name) {
        if (name != null) {
            return service.findByNameContaining(name);
        }
        return service.findAll();
    }

    @GetMapping(value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Course findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Course create(@RequestBody Course course) {
        return service.create(course);
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Course update(@PathVariable("id") Long id ,@RequestBody Course course) {
        return service.update(id, course);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}