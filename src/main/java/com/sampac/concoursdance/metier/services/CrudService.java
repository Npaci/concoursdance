package com.sampac.concoursdance.metier.services;

import com.sampac.concoursdance.exceptions.AlreadyExistException;
import com.sampac.concoursdance.exceptions.ElementNotFoundException;
import com.sampac.concoursdance.exceptions.NotFoundException;

import java.util.List;

public interface CrudService<ENTITY, ID>{
    ENTITY getByID(ID id) throws ElementNotFoundException;
    List<ENTITY> getAll();
    void insert(ENTITY e) throws AlreadyExistException;
    void update(ENTITY e) throws ElementNotFoundException;
    void delete(ID id) throws NotFoundException;
}
