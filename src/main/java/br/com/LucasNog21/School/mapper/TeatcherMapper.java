package br.com.LucasNog21.School.mapper;

import br.com.LucasNog21.School.dto.TeatcherDTO;
import br.com.LucasNog21.School.model.Teatcher;
import br.com.LucasNog21.School.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeatcherMapper {

    @Mapping(source = "subjects", target = "subjects")
    TeatcherDTO teatcherToTeatcherDTO(Teatcher teatcher);

    @Mapping(source = "subjects", target = "subjects")
    Teatcher teatcherDTOToTeatcher(TeatcherDTO dto);

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