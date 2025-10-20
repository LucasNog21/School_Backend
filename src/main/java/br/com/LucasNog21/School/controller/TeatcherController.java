package br.com.LucasNog21.School.controller;


import br.com.LucasNog21.School.controller.docs.TeatcherControllerDocs;
import br.com.LucasNog21.School.dto.TeatcherDTO;
import br.com.LucasNog21.School.model.Teatcher;
import br.com.LucasNog21.School.service.TeatcherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professores")
public class TeatcherController implements TeatcherControllerDocs {

    @Autowired
    private TeatcherServices service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeatcherDTO> findAll(@RequestParam(value="disciplina", required = false) Long id, @RequestParam(value="nome", required = false) String name) {
        if (id != null) {
            return service.findBySubjects_Id(id);
        }
        if (name != null) {
            return service.findByNameContaining(name);
        }
        return service.findAll();
    }

    @GetMapping(value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TeatcherDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TeatcherDTO create(@RequestBody TeatcherDTO teatcherDTO) {
        Teatcher teatcher = service.create(teatcherDTO);
        return new TeatcherDTO(teatcher);
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TeatcherDTO update(@PathVariable("id") Long id , @RequestBody TeatcherDTO teatcherDTO) {
        return service.update(id, teatcherDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

