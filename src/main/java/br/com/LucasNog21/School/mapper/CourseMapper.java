package br.com.LucasNog21.School.mapper;

import br.com.LucasNog21.School.dto.CourseDTO;
import br.com.LucasNog21.School.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO courseToCourseDTO(Course course);
    Course courseDTOToCourse(CourseDTO dto);

}
