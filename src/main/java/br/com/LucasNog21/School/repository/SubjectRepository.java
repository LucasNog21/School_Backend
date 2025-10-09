package br.com.LucasNog21.School.repository;

import br.com.LucasNog21.School.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
