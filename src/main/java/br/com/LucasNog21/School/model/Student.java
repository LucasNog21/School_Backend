package br.com.LucasNog21.School.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
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
            joinColumns = @JoinColumn(name="studenr_id"),
            inverseJoinColumns = @JoinColumn(name= "subject_id")
    )
    private Set<Subject> subjects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return registration == student.registration && Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(course, student.course) && Objects.equals(subjects, student.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, registration, course, subjects);
    }
}