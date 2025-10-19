package br.com.LucasNog21.School.repository;

import br.com.LucasNog21.School.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourseId(Long courseId);
    @Query("""
    SELECT s FROM Student s
    WHERE (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
      AND (:registration IS NULL OR CAST(s.registration AS string) LIKE CONCAT('%', :registration, '%'))
      AND (:course IS NULL OR s.course.id = :course)
""")
    List<Student> search(
            @Param("name") String name,
            @Param("registration") String registration,
            @Param("course") Long course);
}
