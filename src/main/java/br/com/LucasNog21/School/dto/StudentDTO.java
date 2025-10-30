package br.com.LucasNog21.School.dto;

import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.model.Subject;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
public class StudentDTO {

    private Long id;
    private String name;
    private int registration;
    private Long course;
    private List<Long> subjects;

    public StudentDTO(Student student) {
        BeanUtils.copyProperties(student, this);
        this.course = student.getCourse().getId();
        this.subjects = student.getSubjects().stream().map(Subject::getId).toList();
    }

}