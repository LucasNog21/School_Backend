/*package br.com.LucasNog21.School.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class TeatcherController {

    @Autowired
    private TeatcherServices service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeatcherDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(value="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TeatcherDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TeatcherDTO create(@RequestBody TeatcherRequestDTO teatcherDTO) {
        Teatcher teatcher = service.create(teatcherDTO);
        return new TeatcherDTO(teatcher);
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TeatcherDTO update(@PathVariable("id") Long id ,@RequestBody TeatcherRequestDTO teatcherDTO) {
        return service.update(id, teatcherDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
*/
