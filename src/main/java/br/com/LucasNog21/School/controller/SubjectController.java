package br.com.LucasNog21.School.controller;


import br.com.LucasNog21.School.dto.SubjectDTO;
import br.com.LucasNog21.School.model.Subject;
import br.com.LucasNog21.School.service.SubjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/disciplinas")
public class SubjectController {

    @Autowired
    private SubjectServices service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubjectDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SubjectDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SubjectDTO create(@RequestBody SubjectDTO subjectDTO) {
        Subject subject = service.create(subjectDTO);
        return new SubjectDTO(subject);
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SubjectDTO update(@PathVariable("id") Long id ,@RequestBody SubjectDTO subjectDTO) {
        return service.update(id, subjectDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}