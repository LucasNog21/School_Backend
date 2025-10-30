package br.com.LucasNog21.School.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Data @NoArgsConstructor
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 80)
    private String name;

    @Column(name="registration", nullable = false, unique = true, length= 14)
    private int registration;

    @ManyToOne
    @JoinColumn(name="id_course")
    private Course course;

    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name= "subject_id")
    )
    private List<Subject> subjects;

}