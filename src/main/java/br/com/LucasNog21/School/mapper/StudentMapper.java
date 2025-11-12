package br.com.LucasNog21.School.mapper;

import br.com.LucasNog21.School.dto.StudentDTO;
import br.com.LucasNog21.School.model.Course;
import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "course.id", target = "course")
    @Mapping(source = "subjects", target = "subjects")
    StudentDTO studentToStudentDTO(Student student);

    @Mapping(source = "course", target = "course")
    @Mapping(source = "subjects", target = "subjects")
    Student studentDTOToStudent(StudentDTO dto);

    default Long map(Course course) {
        return course == null ? null : course.getId();
    }

    default Course map(Long id) {
        if (id == null) return null;
        Course course = new Course();
        course.setId(id);
        return course;
    }

    default List<Long> mapSubjectsToIds(List<Subject> subjects) {
        if (subjects == null) return null;
        return subjects.stream()
                .map(Subject::getId)
                .collect(Collectors.toList());
    }

    default List<Subject> mapIdsToSubjects(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            Subject s = new Subject();
            s.setId(id);
            return s;
        }).collect(Collectors.toList());
    }
}