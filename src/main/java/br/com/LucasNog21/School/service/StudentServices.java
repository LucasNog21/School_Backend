package br.com.LucasNog21.School.service;

import br.com.LucasNog21.School.exception.ResourceNotFoundException;
import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.repository.StudentRepository;
import jakarta.annotation.Resource;
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

    public List<Student> findAll() {
        logger.info("Encontrando todos os alunos!");

        return repository.findAll();
    }

    public Student findById(Long id) {
        logger.info("Encontrando um aluno!");

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem informações para esse Id!"));
    }

    public Student create(Student student) {
        logger.info("Criando um aluno!");

        return repository.save(student);
    }

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

    public void delete(long id) {
        logger.info("Deletando um aluno");
        Student entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse id!"));
        repository.delete(entity);

    }
}