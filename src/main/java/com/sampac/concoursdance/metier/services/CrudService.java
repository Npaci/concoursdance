package com.sampac.concoursdance.metier.services;

import java.util.List;

public interface CrudService<ENTITY, ID>{
    ENTITY getByID(ID id);
    List<ENTITY> getAll();
    void insert(ENTITY e);
    void update(ENTITY e);
    void delete(ID id);
}
