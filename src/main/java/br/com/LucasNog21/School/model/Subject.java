package br.com.LucasNog21.School.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data @NoArgsConstructor
@Table(name="subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="code", nullable = false, unique = true, length = 80)
    private String code;

    @ManyToOne
    @JoinColumn(name="id_course")
    private Course course;

    @ManyToMany
    @JoinTable(
            name = "subject_teatcher",
            joinColumns = @JoinColumn(name="subject_id"),
            inverseJoinColumns = @JoinColumn(name= "teatcher_id")
    )
    private List<Teatcher> teatchers;

    @ManyToMany(mappedBy="subjects")
    private List<Student> students;

}