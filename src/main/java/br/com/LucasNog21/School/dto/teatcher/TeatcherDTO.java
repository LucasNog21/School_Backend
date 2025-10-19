package br.com.LucasNog21.School.dto.teatcher;

import br.com.LucasNog21.School.model.Subject;
import br.com.LucasNog21.School.model.Teatcher;
import org.springframework.beans.BeanUtils;


import java.util.List;

public class TeatcherDTO {

    private Long id;
    private String name;
    private int registration;
    private List<String> subjects;

    public TeatcherDTO(){

    }

    public TeatcherDTO(Teatcher teatcher) {
        BeanUtils.copyProperties(teatcher, this);
        this.subjects = teatcher.getSubjects().stream().map(Subject::getCode).toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
}
