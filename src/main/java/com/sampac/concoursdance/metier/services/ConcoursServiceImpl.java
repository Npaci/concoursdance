package com.sampac.concoursdance.metier.services;

import com.sampac.concoursdance.dataaccess.entities.Concours;
import com.sampac.concoursdance.dataaccess.repositories.ConcoursRepository;
import com.sampac.concoursdance.exceptions.AlreadyExistException;
import com.sampac.concoursdance.exceptions.NotFoundException;
import com.sampac.concoursdance.metier.dto.ConcoursDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcoursServiceImpl implements CrudService<Concours, Long> {
    private ConcoursRepository repository;

    public ConcoursServiceImpl(ConcoursRepository repository) {
        this.repository = repository;
    }

    @Override
    public Concours getByID(Long id) throws NotFoundException {
        if (!repository.existsById(id))
            throw new NotFoundException("Aucun concours possède cet ID");

        return repository.findById(id).get();
    }

    @Override
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
    }
}