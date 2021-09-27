package com.sampac.concoursdance.metier.mappers;

public interface Mapper<ENTITY, DTO>{
    DTO entityToDTO(ENTITY e);
    ENTITY dtoToEntity(DTO dto);
}
