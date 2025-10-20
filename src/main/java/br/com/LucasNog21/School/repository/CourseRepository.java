package br.com.LucasNog21.School.repository;

import br.com.LucasNog21.School.dto.CourseDTO;
import br.com.LucasNog21.School.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<CourseDTO> findByNameContaining (String name);
}
