package br.com.LucasNog21.School.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data @NoArgsConstructor
@Table(name="teatcher")
public class Teatcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 80)
    private String name;

    @Column(name="registration", nullable = false, unique = true)
    private int registration;

    @ManyToMany(mappedBy="teatchers")
    private List<Subject> subjects;

}