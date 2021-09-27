package com.sampac.concoursdance.metier.mappers;

import com.sampac.concoursdance.dataaccess.entities.Concours;
import com.sampac.concoursdance.metier.dto.ConcoursDTO;

public class ConcoursMapper implements Mapper<Concours, ConcoursDTO> {
    @Override
    public ConcoursDTO entityToDTO(Concours e) {
        return ConcoursDTO.builder()
                .id(e.getId())
                .theme(e.getTheme())
                .description(e.getDescription())
                .date(e.getDate())
                //Candidates
                //Juries
                .build();
    }

    @Override
    public Concours dtoToEntity(ConcoursDTO dto) {
        return Concours.builder()
                .id(dto.getId())
                .theme(dto.getTheme())
                .description(dto.getDescription())
                .date(dto.getDate())
                //Candidates
                //Juries
                .build();
    }
}
