package br.com.LucasNog21.School.dto;

import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.model.Subject;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class StudentDTO {

    private Long id;
    private String name;
    private int registration;
    private String course;
    private List<String> subjects;

    public StudentDTO() {

    }

    public StudentDTO(Student student) {
        BeanUtils.copyProperties(student, this);
        this.course = student.getCourse().getName();
        this.subjects = student.getSubjects().stream().map(Subject::getCode).toList();

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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
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