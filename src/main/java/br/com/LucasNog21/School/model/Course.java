package br.com.LucasNog21.School.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data @NoArgsConstructor
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 80)
    private String name;

    @Column(name="code", nullable = false, length = 80, unique = true)
    private String code;

}