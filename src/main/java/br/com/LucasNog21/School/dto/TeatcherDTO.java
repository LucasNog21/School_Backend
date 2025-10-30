package br.com.LucasNog21.School.dto;

import br.com.LucasNog21.School.model.Subject;
import br.com.LucasNog21.School.model.Teatcher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


import java.util.List;


@Getter @Setter
@NoArgsConstructor
public class TeatcherDTO {

    private Long id;
    private String name;
    private int registration;
    private List<Long> subjects;

    public TeatcherDTO(Teatcher teatcher) {
        BeanUtils.copyProperties(teatcher, this);
        this.subjects = teatcher.getSubjects().stream().map(Subject::getId).toList();
    }

}
