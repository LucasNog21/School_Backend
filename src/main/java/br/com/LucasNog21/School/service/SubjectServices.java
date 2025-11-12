package br.com.LucasNog21.School.service;

import br.com.LucasNog21.School.dto.SubjectDTO;
import br.com.LucasNog21.School.exception.ResourceNotFoundException;
import br.com.LucasNog21.School.mapper.SubjectMapper;
import br.com.LucasNog21.School.model.Course;
import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.model.Subject;
import br.com.LucasNog21.School.model.Teatcher;
import br.com.LucasNog21.School.repository.CourseRepository;
import br.com.LucasNog21.School.repository.StudentRepository;
import br.com.LucasNog21.School.repository.SubjectRepository;
import br.com.LucasNog21.School.repository.TeatcherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class SubjectServices
{
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(SubjectServices.class.getName());

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TeatcherRepository teatcherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectMapper mapper;


    @Transactional
    public List<SubjectDTO> findAll() {
        logger.info("Encontrando todas as disciplinas!");

        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream().map(mapper::subjectToSubjectDTO).toList();
    }

    @Transactional
    public SubjectDTO findById(Long id) {
        logger.info("Encontrando uma disciplina!");
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem informações para esse Id!"));
        return mapper.subjectToSubjectDTO(subject);
    }


    @Transactional
    public Subject create(SubjectDTO subjectDTO) {
        logger.info("Criando uma disciplina!");
        Subject subject = mapper.subjectDTOToSubject(subjectDTO);

        Course course = courseRepository.findById(subjectDTO.getCourse()).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        subject.setCourse(course);

        List<Teatcher> teatchers = teatcherRepository.findAllById(subjectDTO.getTeatchers());
        subject.setTeatchers(teatchers);

        List<Student> students = studentRepository.findAllById(subjectDTO.getStudents());
        subject.setStudents(students);


        return subjectRepository.save(subject);
    }



    @Transactional
    public SubjectDTO update(Long id, SubjectDTO subjectDTO) {
        logger.info("Atualizando uma disciplina!");
        Subject entity = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse Id!"));

        entity.setCode(subjectDTO.getCode());
        Course course = courseRepository.findById(subjectDTO.getCourse())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado!"));
        entity.setCourse(course);
        List<Teatcher> teatchers = teatcherRepository.findAllById(subjectDTO.getTeatchers());
        entity.setTeatchers(teatchers);
        List<Student> students = studentRepository.findAllById(subjectDTO.getStudents());
        entity.setStudents(students);


        Subject updated = mapper.subjectDTOToSubject(subjectDTO);
        updated.setId(entity.getId());

        Subject saved = subjectRepository.save(updated);
        return mapper.subjectToSubjectDTO(saved);



    }

    @Transactional
    public void delete(long id) {
        logger.info("Deletando uma disciplina");
        Subject entity = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse id!"));
        subjectRepository.delete(entity);

    }
}