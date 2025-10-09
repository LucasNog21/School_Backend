package br.com.LucasNog21.School.repository;

import br.com.LucasNog21.School.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
