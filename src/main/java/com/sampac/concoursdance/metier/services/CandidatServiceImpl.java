package com.sampac.concoursdance.metier.services;

import com.sampac.concoursdance.dataaccess.entities.Candidat;
import com.sampac.concoursdance.dataaccess.repositories.CandidatRepository;
import com.sampac.concoursdance.exceptions.AlreadyExistException;
import com.sampac.concoursdance.exceptions.ElementNotFoundException;
import com.sampac.concoursdance.exceptions.NotFoundException;
import com.sampac.concoursdance.metier.dto.CandidatDTO;
import com.sampac.concoursdance.metier.mappers.CandidatMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatServiceImpl implements CrudService<CandidatDTO, Long> {
    private CandidatRepository repository;
    private CandidatMapper mapper;

    public CandidatServiceImpl(CandidatRepository repository, CandidatMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CandidatDTO getByID(Long id) throws ElementNotFoundException {
        if (!repository.existsById(id))
           // throw new NotFoundException("Aucun candidat possède cet ID");
            new ElementNotFoundException();

        return repository.findById(id)
                .map(mapper::entityToDTO)
                .orElseThrow(ElementNotFoundException::new);
    }

    @Override
    public List<CandidatDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void insert(CandidatDTO dto) throws AlreadyExistException {
        if (repository.existsById(dto.getId()))
            throw new AlreadyExistException("Ce candidat existe déjà");

        repository.save(mapper.dtoToEntity(dto));
    }

    @Override
    public void update(CandidatDTO dto) throws ElementNotFoundException {
        if (!repository.existsById(dto.getId()))
            throw new ElementNotFoundException();

        repository.save(mapper.dtoToEntity(dto));
    }

    @Override
    public void delete(Long id) throws ElementNotFoundException {
        if (!repository.existsById(id))
            throw new ElementNotFoundException();

        repository.deleteById(id);
    }
}