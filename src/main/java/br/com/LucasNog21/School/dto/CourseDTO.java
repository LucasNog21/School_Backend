package br.com.LucasNog21.School.dto;

import br.com.LucasNog21.School.model.Course;
import org.springframework.beans.BeanUtils;

public class CourseDTO {

    private Long id;
    private String name;
    private String code;

    public CourseDTO() {

    }

    public CourseDTO(Course course) {
        BeanUtils.copyProperties(course, this);
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}