package br.com.LucasNog21.School.service;

import br.com.LucasNog21.School.dto.StudentDTO;
import br.com.LucasNog21.School.dto.StudentRequestDTO;
import br.com.LucasNog21.School.exception.ResourceNotFoundException;
import br.com.LucasNog21.School.model.Course;
import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.model.Subject;
import br.com.LucasNog21.School.repository.CourseRepository;
import br.com.LucasNog21.School.repository.StudentRepository;
import br.com.LucasNog21.School.repository.SubjectRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class StudentServices
{
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(StudentServices.class.getName());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Transactional
    public List<StudentDTO> findAll() {
        logger.info("Encontrando todos os alunos!");

        List<Student> students = studentRepository.findAll();
        return students.stream().map(StudentDTO::new).toList();
    }

    @Transactional
    public StudentDTO findById(Long id) {
        logger.info("Encontrando um aluno!");
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem informações para esse Id!"));
        StudentDTO studentDTO = new StudentDTO(student);
        return studentDTO;
    }


    @Transactional
    public Student create(StudentRequestDTO studentDTO) {
        logger.info("Criando um aluno!");
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setRegistration(studentDTO.getRegistration());

        Course course = courseRepository.findById(studentDTO.getCourseId()).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        student.setCourse(course);

        List<Subject> subjects = subjectRepository.findAllById(studentDTO.getSubjectId());
        student.setSubjects(subjects);


        return studentRepository.save(student);
    }



    @Transactional
    public Student update(Student student) {
        logger.info("Atualizando um aluno!");
        Student entity = studentRepository.findById(student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse Id!"));

        entity.setName(student.getName());
        entity.setRegistration(student.getRegistration());
        entity.setCourse(student.getCourse());
        entity.setSubjects(student.getSubjects());

        return studentRepository.save(student);

    }

    @Transactional
    public void delete(long id) {
        logger.info("Deletando um aluno");
        Student entity = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse id!"));
        studentRepository.delete(entity);

    }
}