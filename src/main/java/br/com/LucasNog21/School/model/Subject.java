package br.com.LucasNog21.School.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
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
    private Set<Teatcher> teatchers;

    @ManyToMany(mappedBy="subjects")
    private Set<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Teatcher> getTeatchers() {
        return teatchers;
    }

    public void setTeatchers(Set<Teatcher> teatchers) {
        this.teatchers = teatchers;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}