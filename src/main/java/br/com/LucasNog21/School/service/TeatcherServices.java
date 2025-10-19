package br.com.LucasNog21.School.service;

import br.com.LucasNog21.School.dto.StudentDTO;
import br.com.LucasNog21.School.dto.TeatcherDTO;
import br.com.LucasNog21.School.exception.ResourceNotFoundException;
import br.com.LucasNog21.School.model.Student;
import br.com.LucasNog21.School.model.Teatcher;
import br.com.LucasNog21.School.model.Subject;
import br.com.LucasNog21.School.repository.TeatcherRepository;
import br.com.LucasNog21.School.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class TeatcherServices
{
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(TeatcherServices.class.getName());

    @Autowired
    TeatcherRepository teatcherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Transactional
    public List<TeatcherDTO> findAll() {
        logger.info("Encontrando todos os professores!");

        List<Teatcher> teatchers = teatcherRepository.findAll();
        return teatchers.stream().map(TeatcherDTO::new).toList();
    }

    @Transactional
    public TeatcherDTO findById(Long id) {
        logger.info("Encontrando um professor!");
        Teatcher teatcher = teatcherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem informações para esse Id!"));
        TeatcherDTO teatcherDTO = new TeatcherDTO(teatcher);
        return teatcherDTO;
    }


    @Transactional
    public Teatcher create(TeatcherDTO teatcherDTO) {
        logger.info("Criando um professor!");
        Teatcher teatcher = new Teatcher();
        teatcher.setName(teatcherDTO.getName());
        teatcher.setRegistration(teatcherDTO.getRegistration());


        List<Subject> subjects = subjectRepository.findAllById(teatcherDTO.getSubjects());
        teatcher.setSubjects(subjects);


        return teatcherRepository.save(teatcher);
    }



    @Transactional
    public TeatcherDTO update(Long id, TeatcherDTO teatcherDTO) {
        logger.info("Atualizando um professor!");
        Teatcher entity = teatcherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse Id!"));

        entity.setName(teatcherDTO.getName());
        entity.setRegistration(teatcherDTO.getRegistration());
        List<Subject> subjects = subjectRepository.findAllById(teatcherDTO.getSubjects());
        entity.setSubjects(subjects);

        Teatcher updated = teatcherRepository.save(entity);

        TeatcherDTO updatedDTO = new TeatcherDTO();
        updatedDTO.setId(updated.getId());
        updatedDTO.setName(updated.getName());
        updatedDTO.setRegistration(updated.getRegistration());
        updatedDTO.setSubjects(updated.getSubjects().stream()
                .map(Subject::getId)
                .toList());

        return updatedDTO;



    }

    @Transactional
    public void delete(long id) {
        logger.info("Deletando um professor");
        Teatcher entity = teatcherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse id!"));
        teatcherRepository.delete(entity);

    }

    @Transactional
    public List<TeatcherDTO> findBySubjects_Id(Long id) {
        logger.info("Buscando professores da disciplina com ID:" + id);
        List<Teatcher> teatchers = teatcherRepository.findBySubjects_Id(id);
        return teatchers.stream().map(TeatcherDTO::new).toList();
    }

}
