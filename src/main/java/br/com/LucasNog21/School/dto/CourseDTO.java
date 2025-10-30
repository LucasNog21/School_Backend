package br.com.LucasNog21.School.dto;

import br.com.LucasNog21.School.model.Course;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter @Setter
@NoArgsConstructor
public class CourseDTO {

    private Long id;
    private String name;
    private String code;

    public CourseDTO(Course course) {
        BeanUtils.copyProperties(course, this);
    }

}