package br.com.LucasNog21.School.mapper;

import br.com.LucasNog21.School.dto.CourseDTO;
import br.com.LucasNog21.School.dto.StudentDTO;
import br.com.LucasNog21.School.model.Course;
import br.com.LucasNog21.School.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO courseToCourseDTO(Course course);
    Course courseDTOToCourse(CourseDTO dto);

}
