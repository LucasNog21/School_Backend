package br.com.LucasNog21.School.dto;

import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.model.Subject;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

public class StudentDTO {

    private Long id;
    private String name;
    private int registration;
    private Long course;
    private List<Long> subjects;

    public StudentDTO() {

    }

    public StudentDTO(Student student) {
        BeanUtils.copyProperties(student, this);
        this.course = student.getCourse().getId();
        this.subjects = student.getSubjects().stream().map(Subject::getId).toList();

    }

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

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public List<Long> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Long> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StudentDTO student)) return false;
        return registration == student.registration && Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(course, student.course) && Objects.equals(subjects, student.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, registration, course, subjects);
    }
}