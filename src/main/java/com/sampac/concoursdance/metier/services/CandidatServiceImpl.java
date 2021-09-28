package com.sampac.concoursdance.metier.services;

import com.sampac.concoursdance.dataaccess.entities.Candidat;
import com.sampac.concoursdance.dataaccess.repositories.CandidatRepository;
import com.sampac.concoursdance.exceptions.AlreadyExistException;
import com.sampac.concoursdance.exceptions.ElementNotFoundException;
import com.sampac.concoursdance.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatServiceImpl implements CrudService<Candidat, Long> {
    private CandidatRepository repository;

    public CandidatServiceImpl(CandidatRepository repository) {
        this.repository = repository;
    }

    @Override
    public Candidat getByID(Long id) throws ElementNotFoundException {
        if (!repository.existsById(id))
           // throw new NotFoundException("Aucun candidat possède cet ID");
            new ElementNotFoundException();

        return repository.findById(id).get();
    }

    @Override
    public List<Candidat> getAll() {
        return repository.findAll();
    }

    @Override
    public void insert(Candidat e) throws AlreadyExistException {
        if (repository.existsById(e.getId_Candidat()))
            throw new AlreadyExistException("Ce candidat existe déjà");

        repository.save(e);
    }

    @Override
    public void update(Candidat e) throws ElementNotFoundException {
        if (!repository.existsById(e.getId_Candidat()))
            throw new ElementNotFoundException();

        repository.save(e);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if (!repository.existsById(id))
            throw new NotFoundException("Aucun candidat possède cet ID");

        repository.deleteById(id);
    }
}