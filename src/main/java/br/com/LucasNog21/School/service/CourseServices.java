package br.com.LucasNog21.School.service;

import br.com.LucasNog21.School.model.Course;
import br.com.LucasNog21.School.exception.ResourceNotFoundException;
import br.com.LucasNog21.School.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class CourseServices
{
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(CourseServices.class.getName());

    @Autowired
    CourseRepository courseRepository;



    @Transactional
    public List<Course> findAll() {
        logger.info("Encontrando todos os cursos!");

        return courseRepository.findAll();
    }

    @Transactional
    public Course findById(Long id) {
        logger.info("Encontrando um cursos!");
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem informações para esse Id!"));
    }


    @Transactional
    public Course create(Course course) {
        logger.info("Criando um curso!");
        return courseRepository.save(course);
    }



    @Transactional
    public Course update(Long id, Course course) {
        logger.info("Atualizando um curso!");
        Course entity = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse Id!"));

        entity.setName(course.getName());
        entity.setCode(course.getCode());

        return courseRepository.save(entity);
    }

    @Transactional
    public void delete(long id) {
        logger.info("Deletando um curso");
        Course entity = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse id!"));
        courseRepository.delete(entity);

    }
}