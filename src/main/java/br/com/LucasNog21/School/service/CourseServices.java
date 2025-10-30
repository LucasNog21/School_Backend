package br.com.LucasNog21.School.service;

import br.com.LucasNog21.School.dto.CourseDTO;
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
    public List<CourseDTO> findAll() {
        logger.info("Encontrando todos os cursos!");

        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(CourseDTO::new).toList();
    }

    @Transactional
    public CourseDTO findById(Long id) {
        logger.info("Encontrando um curso!");
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem informações para esse Id!"));
        CourseDTO courseDTO = new CourseDTO(course);
        return courseDTO;
    }


    @Transactional
    public Course create(CourseDTO courseDTO) {
        logger.info("Criando um curso!");
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setCode(courseDTO.getCode());

        return courseRepository.save(course);

    }



    @Transactional
    public CourseDTO update(Long id, CourseDTO courseDTO) {
        logger.info("Atualizando um curso!");
        Course entity = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse Id!"));

        entity.setName(courseDTO.getName());
        entity.setCode(courseDTO.getCode());

        Course updated = courseRepository.save(entity);

        CourseDTO updatedDTO = new CourseDTO();
        updatedDTO.setId(updated.getId());
        updatedDTO.setName(updated.getName());
        updatedDTO.setCode(updated.getCode());

        return updatedDTO;

    }

    @Transactional
    public void delete(long id) {
        logger.info("Deletando um curso");
        Course entity = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse id!"));
        courseRepository.delete(entity);

    }

    @Transactional
    public List<CourseDTO> findByNameContaining(String name) {
        logger.info("Buscando cursos pelo nome");
        return courseRepository.findByNameContaining(name);
    }
}