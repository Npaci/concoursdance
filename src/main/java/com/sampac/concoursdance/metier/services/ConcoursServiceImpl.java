package com.sampac.concoursdance.metier.services;

import com.sampac.concoursdance.dataaccess.entities.Concours;
import com.sampac.concoursdance.dataaccess.repositories.ConcoursRepository;
import com.sampac.concoursdance.exceptions.AlreadyExistException;
import com.sampac.concoursdance.exceptions.ElementNotFoundException;
import com.sampac.concoursdance.exceptions.NotFoundException;
import com.sampac.concoursdance.metier.dto.ConcoursDTO;
import com.sampac.concoursdance.metier.mappers.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcoursServiceImpl implements CrudService<ConcoursDTO, Long> {
    private ConcoursRepository repository;
    private final Mapper<Concours, ConcoursDTO> mapper;

    public ConcoursServiceImpl(ConcoursRepository repository, Mapper<Concours, ConcoursDTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ConcoursDTO getByID(Long id) throws ElementNotFoundException {
        return repository.findById(id)
                .map(mapper::entityToDTO)
                .orElseThrow(ElementNotFoundException::new);

    }

    @Override
    public List<ConcoursDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insert(ConcoursDTO e) throws AlreadyExistException {
        if( repository.existsById(e.getId()) )
            throw new AlreadyExistException("Ce concours existe déjà");
        repository.save( mapper.dtoToEntity(e));
    }

    @Override
    public void update(ConcoursDTO e) throws ElementNotFoundException {
        if( !repository.existsById(e.getId()) )
            throw new ElementNotFoundException();

        repository.save( mapper.dtoToEntity(e) );
    }

    @Override
    public void delete(Long aLong) throws NotFoundException {

    }

   /* @Override
    public List<Concours> getAll() {
        return repository.findAll();
    }

    @Override
    public void insert(Concours e) throws AlreadyExistException {
        if (repository.existsById(e.getId_Concour()))
            throw new AlreadyExistException("Ce concours existe déjà");

        repository.save(e);
    }

    @Override
    public void update(Concours e) throws NotFoundException {
        if (!repository.existsById(e.getId_Concour()))
            throw new NotFoundException("Aucun concours possède cet ID");

        repository.save(e);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if (!repository.existsById(id))
            throw new NotFoundException("Aucun concours possède cet ID");

        repository.deleteById(id);
    }*/
}