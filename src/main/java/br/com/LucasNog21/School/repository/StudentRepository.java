package br.com.LucasNog21.School.repository;

import br.com.LucasNog21.School.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
