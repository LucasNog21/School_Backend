package br.com.LucasNog21.School.service;

import br.com.LucasNog21.School.dto.StudentDTO;
import br.com.LucasNog21.School.dto.TeatcherDTO;
import br.com.LucasNog21.School.exception.ResourceNotFoundException;
import br.com.LucasNog21.School.mapper.TeatcherMapper;
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

    @Autowired
    TeatcherMapper mapper;

    @Transactional
    public List<TeatcherDTO> findAll() {
        logger.info("Encontrando todos os professores!");

        List<Teatcher> teatchers = teatcherRepository.findAll();
        return teatchers.stream().map(mapper::teatcherToTeatcherDTO).toList();
    }

    @Transactional
    public TeatcherDTO findById(Long id) {
        logger.info("Encontrando um professor!");
        Teatcher teatcher = teatcherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem informações para esse Id!"));
        return mapper.teatcherToTeatcherDTO(teatcher);
    }


    @Transactional
    public Teatcher create(TeatcherDTO teatcherDTO) {
        logger.info("Criando um professor!");
        Teatcher teatcher = mapper.teatcherDTOToTeatcher(teatcherDTO);

        List<Subject> subjects = subjectRepository.findAllById(teatcherDTO.getSubjects());
        teatcher.setSubjects(subjects);

        return teatcherRepository.save(teatcher);
    }



    @Transactional
    public TeatcherDTO update(Long id, TeatcherDTO teatcherDTO) {
        logger.info("Atualizando um professor!");
        Teatcher entity = teatcherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registros para esse Id!"));

        List<Subject> subjects = subjectRepository.findAllById(teatcherDTO.getSubjects());
        entity.setSubjects(subjects);

        Teatcher updated = mapper.teatcherDTOToTeatcher(teatcherDTO);
        updated.setId(entity.getId());

        Teatcher saved = teatcherRepository.save(updated);
        return mapper.teatcherToTeatcherDTO(saved);



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
        return teatchers.stream().map(mapper::teatcherToTeatcherDTO).toList();
    }

    @Transactional
    public List<TeatcherDTO> findByNameContaining(String name) {
        logger.info("Buscando professores com o nome:" + name);
        List<Teatcher> teatchers = teatcherRepository.findByNameContaining(name);
        return teatchers.stream().map(mapper::teatcherToTeatcherDTO).toList();
    }

}
