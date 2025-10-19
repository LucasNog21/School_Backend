package br.com.LucasNog21.School.dto;

import java.util.ArrayList;
import java.util.List;

public class StudentRequestDTO {
    private String name;
    private int registration;
    private Long courseId;
    private List<Long> subjectId = new ArrayList<>();

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public List<Long> getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(List<Long> subjectId) {
        this.subjectId = subjectId;
    }
}