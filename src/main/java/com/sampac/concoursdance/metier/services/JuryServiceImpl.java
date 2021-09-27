package com.sampac.concoursdance.metier.services;

import com.sampac.concoursdance.dataaccess.entities.Jury;
import com.sampac.concoursdance.dataaccess.repositories.JuryRepository;
import com.sampac.concoursdance.exceptions.AlreadyExistException;
import com.sampac.concoursdance.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuryServiceImpl implements CrudService<Jury, Long> {
    private JuryRepository repository;

    public JuryServiceImpl(JuryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Jury getByID(Long id) throws NotFoundException {
        if (!repository.existsById(id))
            throw new NotFoundException("Aucun jury possède cet ID");

        return repository.findById(id).get();
    }

    @Override
    public List<Jury> getAll() {
        return repository.findAll();
    }

    @Override
    public void insert(Jury e) throws AlreadyExistException {
        if (repository.existsById(e.getId()))
            throw new AlreadyExistException("Ce jury existe déjà");

        repository.save(e);
    }

    @Override
    public void update(Jury e) throws NotFoundException {
        if (!repository.existsById(e.getId()))
            throw new NotFoundException("Aucun jury possède cet ID");

        repository.save(e);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if (!repository.existsById(id))
            throw new NotFoundException("Aucun jury possède cet ID");

        repository.deleteById(id);
    }
}
