package br.com.LucasNog21.School.controller;


import br.com.LucasNog21.School.controller.docs.StudentControllerDocs;
import br.com.LucasNog21.School.dto.StudentDTO;
import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.service.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alunos")
public class StudentController implements StudentControllerDocs {

    @Autowired
    private StudentServices service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<StudentDTO> findAll(@RequestParam(value="curso", required = false) Long courseId) {
        if (courseId != null) {
            return service.findByCourseId(courseId);
        }
        return service.findAll();
    }

    @GetMapping(value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public StudentDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public StudentDTO create(@RequestBody StudentDTO studentDTO) {
        Student student = service.create(studentDTO);
        return new StudentDTO(student);
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public StudentDTO update(@PathVariable("id") Long id ,@RequestBody StudentDTO studentDTO) {
        return service.update(id, studentDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDTO> search(
            @RequestParam(value="nome", required = false) String name,
            @RequestParam(value = "matricula", required = false) String registration,
            @RequestParam(value = "curso", required = false) Long course) {
        return service.search(name, registration, course);

    }

}