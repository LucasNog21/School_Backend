package br.com.LucasNog21.School.dto;

import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.model.Subject;
import br.com.LucasNog21.School.model.Teatcher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class SubjectDTO {

    private Long id;
    private String code;
    private Long course;
    private List<Long> teatchers;
    private List<Long> students;

    public SubjectDTO(Subject subject) {
        BeanUtils.copyProperties(subject, this);
        this.course = subject.getCourse().getId();;
        this.teatchers = subject.getTeatchers().stream().map(Teatcher::getId).toList();
        this.students = subject.getStudents().stream().map(Student::getId).toList();
    }

}