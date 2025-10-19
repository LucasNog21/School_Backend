package br.com.LucasNog21.School.repository;

import br.com.LucasNog21.School.model.Teatcher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeatcherRepository extends JpaRepository<Teatcher, Long> {
    List<Teatcher> findBySubjects_Id(Long id);
    List<Teatcher> findByNameContaining(String name);
}
