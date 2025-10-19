package br.com.LucasNog21.School.dto;

import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.model.Subject;
import br.com.LucasNog21.School.model.Teatcher;
import org.springframework.beans.BeanUtils;

import java.util.List;


public class SubjectDTO {

    private Long id;
    private String code;
    private Long course;
    private List<Long> teatchers;
    private List<Long> students;

    public SubjectDTO() {

    }

    public SubjectDTO(Subject subject) {
        BeanUtils.copyProperties(subject, this);
        this.course = subject.getCourse().getId();;
        this.teatchers = subject.getTeatchers().stream().map(Teatcher::getId).toList();
        this.students = subject.getStudents().stream().map(Student::getId).toList();
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

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public List<Long> getTeatchers() {
        return teatchers;
    }

    public void setTeatchers(List<Long> teatchers) {
        this.teatchers = teatchers;
    }

    public List<Long> getStudents() {
        return students;
    }

    public void setStudents(List<Long> students) {
        this.students = students;
    }
}