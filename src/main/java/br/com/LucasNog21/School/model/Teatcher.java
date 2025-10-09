package br.com.LucasNog21.School.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="teatcher")
public class Teatcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 80)
    private String name;

    @Column(name="registration", nullable = false, unique = true)
    private int registration;

    @ManyToMany(mappedBy="teatchers")
    private Set<Subject> subjects;

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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}