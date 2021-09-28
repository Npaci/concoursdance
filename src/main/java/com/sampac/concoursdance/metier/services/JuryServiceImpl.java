package com.sampac.concoursdance.metier.services;

import com.sampac.concoursdance.dataaccess.entities.Jury;
import com.sampac.concoursdance.dataaccess.repositories.JuryRepository;
import com.sampac.concoursdance.exceptions.AlreadyExistException;
import com.sampac.concoursdance.exceptions.ElementNotFoundException;
import com.sampac.concoursdance.metier.dto.JuryDTO;
import com.sampac.concoursdance.metier.mappers.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JuryServiceImpl implements CrudService<JuryDTO, Long> {
    private JuryRepository repository;
    private final Mapper<Jury, JuryDTO> mapper;

    public JuryServiceImpl(JuryRepository repository, Mapper<Jury, JuryDTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public JuryDTO getByID(Long id) throws ElementNotFoundException {
        return repository.findById(id)
                .map(mapper::entityToDTO)
                .orElseThrow(ElementNotFoundException::new);
    }

    @Override
    public List<JuryDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insert(JuryDTO e) throws AlreadyExistException {
        if( repository.existsById(e.getId()) )
            throw new AlreadyExistException("Ce jury existe déjà");
        repository.save(mapper.dtoToEntity(e));
    }

    @Override
    public void update(JuryDTO e) throws ElementNotFoundException {
        if( !repository.existsById(e.getId()) )
            throw new ElementNotFoundException();

        repository.save(mapper.dtoToEntity(e));
    }

    @Override
    public void delete(Long aLong) throws ElementNotFoundException {
        if( !repository.existsById(aLong) )
            throw new ElementNotFoundException();

        repository.deleteById(aLong);
    }

    /*@Override
    public List<Jury> getAll() {
        return repository.findAll();
    }

    @Override
    public void insert(Jury e) throws AlreadyExistException {
        if (repository.existsById(e.getId_Jury()))
            throw new AlreadyExistException("Ce jury existe déjà");

        repository.save(e);
    }

    @Override
    public void update(Jury e) throws ElementNotFoundException {
        if (!repository.existsById(e.getId_Jury()))
            throw new ElementNotFoundException();

        repository.save(e);
    }

    @Override
    public void delete(Long id) throws ElementNotFoundException {
        if (!repository.existsById(id))
            throw new ElementNotFoundException();

        repository.deleteById(id);
    }*/
}
