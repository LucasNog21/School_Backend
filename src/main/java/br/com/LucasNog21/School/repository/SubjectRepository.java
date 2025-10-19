package br.com.LucasNog21.School.repository;

import br.com.LucasNog21.School.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
