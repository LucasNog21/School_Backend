package br.com.LucasNog21.School.mapper;

import br.com.LucasNog21.School.dto.StudentDTO;
import br.com.LucasNog21.School.dto.SubjectDTO;
import br.com.LucasNog21.School.model.Course;
import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.model.Subject;
import br.com.LucasNog21.School.model.Teatcher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(source = "course.id", target = "course")
    @Mapping(source = "students", target = "students")
    @Mapping(source = "teatchers", target = "teatchers")
    SubjectDTO subjectToSubjectDTO(Subject subject);

    @Mapping(source = "course", target = "course")
    @Mapping(source = "students", target = "students")
    @Mapping(source = "teatchers", target = "teatchers")
    Subject subjectDTOToSubject(SubjectDTO dto);

    default Long map(Course course) {
        return course == null ? null : course.getId();
    }

    default Course map(Long id) {
        if (id == null) return null;
        Course course = new Course();
        course.setId(id);
        return course;
    }

    default List<Long> mapTeatchersToIds(List<Teatcher> teatchers) {
        if (teatchers == null) return null;
        return teatchers.stream()
                .map(Teatcher::getId)
                .collect(Collectors.toList());
    }

    default List<Teatcher> mapIdsToTeatchers(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            Teatcher t = new Teatcher();
            t.setId(id);
            return t;
        }).collect(Collectors.toList());
    }

    default List<Long> mapStudentsToIds(List<Student> students) {
        if (students == null) return null;
        return students.stream()
                .map(Student::getId)
                .collect(Collectors.toList());
    }

    default List<Student> mapIdsToStudents(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            Student s = new Student();
            s.setId(id);
            return s;
        }).collect(Collectors.toList());
    }

}