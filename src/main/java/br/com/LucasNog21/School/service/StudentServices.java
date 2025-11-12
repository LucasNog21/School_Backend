package br.com.LucasNog21.School.service;

import br.com.LucasNog21.School.dto.StudentDTO;
import br.com.LucasNog21.School.exception.RequiredObjectIsNullException;
import br.com.LucasNog21.School.exception.ResourceNotFoundException;
import br.com.LucasNog21.School.mapper.StudentMapper;
import br.com.LucasNog21.School.model.Course;
import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.model.Subject;
import br.com.LucasNog21.School.repository.CourseRepository;
import br.com.LucasNog21.School.repository.StudentRepository;
import br.com.LucasNog21.School.repository.SubjectRepository;
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
    StudentMapper mapper;

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
        return students.stream().map(mapper::studentToStudentDTO).toList();
    }

    @Transactional
    public StudentDTO findById(Long id) {
        logger.info("Encontrando um aluno!");
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem informações para esse Id!"));
        return mapper.studentToStudentDTO(student);
    }


    @Transactional
    public Student create(StudentDTO studentDTO) {
        logger.info("Criando um aluno!");
        if (studentDTO == null) throw new RequiredObjectIsNullException();
        Student student = mapper.studentDTOToStudent(studentDTO);

        Course course = courseRepository.findById(studentDTO.getCourse()).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        student.setCourse(course);

        List<Subject> subjects = subjectRepository.findAllById(studentDTO.getSubjects());
        student.setSubjects(subjects);


        return studentRepository.save(student);
    }



    @Transactional
    public StudentDTO update(Long id, StudentDTO studentDTO) {
        logger.info("Atualizando um aluno!");
        Student entity = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse Id!"));

        Student updated = mapper.studentDTOToStudent(studentDTO);
        updated.setId(entity.getId());
        Course course = courseRepository.findById(studentDTO.getCourse())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado!"));
        entity.setCourse(course);
        List<Subject> subjects = subjectRepository.findAllById(studentDTO.getSubjects());
        entity.setSubjects(subjects);

        Student saved = studentRepository.save(updated);
        return mapper.studentToStudentDTO(saved);
    }

    @Transactional
    public void delete(long id) {
        logger.info("Deletando um aluno");
        Student entity = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse id!"));
        studentRepository.delete(entity);

    }

    @Transactional
    public List<StudentDTO> findByCourseId(Long courseId) {
        logger.info("Buscando alunos do curso com ID:" + courseId);
        List<Student> students = studentRepository.findByCourseId(courseId);
        return students.stream().map(mapper::studentToStudentDTO).toList();
    }

    @Transactional
    public List<StudentDTO> search(String name, String registration, Long course) {
        logger.info("Buscando alunos com matricula igual a: " + registration + " e nome igual a: " + name);
        List<Student> students = studentRepository.search(name, registration, course);
        return students.stream().map(mapper::studentToStudentDTO).toList();
    }
}