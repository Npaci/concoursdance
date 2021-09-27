package com.sampac.concoursdance.metier.mappers;

import com.sampac.concoursdance.dataaccess.entities.Jury;
import com.sampac.concoursdance.metier.dto.JuryDTO;

public class JuryMapper implements Mapper<Jury, JuryDTO> {
    @Override
    public JuryDTO entityToDTO(Jury e) {
        return JuryDTO.builder()
                .id(e.getId())
                .nom(e.getNom())
                .expertise(e.getExpertise())
                //Concours
                .build();
    }

    @Override
    public Jury dtoToEntity(JuryDTO dto) {
        return Jury.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .expertise(dto.getExpertise())
                //Concours
                .build();
    }
}
