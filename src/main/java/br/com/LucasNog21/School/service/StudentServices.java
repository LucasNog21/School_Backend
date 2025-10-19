package br.com.LucasNog21.School.service;

import br.com.LucasNog21.School.dto.StudentDTO;
import br.com.LucasNog21.School.exception.ResourceNotFoundException;
import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.repository.StudentRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class StudentServices
{
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(StudentServices.class.getName());

    @Autowired
    StudentRepository repository;

    @Transactional
    public List<StudentDTO> findAll() {
        logger.info("Encontrando todos os alunos!");

        List<Student> students = repository.findAll();
        return students.stream().map(StudentDTO::new).toList();
    }

    @Transactional
    public StudentDTO findById(Long id) {
        logger.info("Encontrando um aluno!");
        Student student = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem informações para esse Id!"));
        StudentDTO studentDTO = new StudentDTO(student);
        return studentDTO;
    }

    @Transactional
    public Student create(Student student) {
        logger.info("Criando um aluno!");

        return repository.save(student);
    }

    @Transactional
    public Student update(Student student) {
        logger.info("Atualizando um aluno!");
        Student entity = repository.findById(student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse Id!"));

        entity.setName(student.getName());
        entity.setRegistration(student.getRegistration());
        entity.setCourse(student.getCourse());
        entity.setSubjects(student.getSubjects());

        return repository.save(student);

    }

    @Transactional
    public void delete(long id) {
        logger.info("Deletando um aluno");
        Student entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse id!"));
        repository.delete(entity);

    }
}