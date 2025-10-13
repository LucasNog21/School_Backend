package br.com.LucasNog21.School.model;


import jakarta.persistence.*;

import java.util.List;
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
    private List<Teatcher> teatchers;

    @ManyToMany(mappedBy="subjects")
    private List<Student> students;

    public Subject() {

    }

    public Subject(Long id, String code, Course course, List<Teatcher> teatchers, List<Student> students) {
        this.id = id;
        this.code = code;
        this.course = course;
        this.teatchers = teatchers;
        this.students = students;
    }


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

    public List<Teatcher> getTeatchers() {
        return teatchers;
    }

    public void setTeatchers(List<Teatcher> teatchers) {
        this.teatchers = teatchers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}