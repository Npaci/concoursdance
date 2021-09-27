package com.sampac.concoursdance.metier.mappers;

import com.sampac.concoursdance.dataaccess.entities.Candidat;
import com.sampac.concoursdance.metier.dto.CandidatDTO;

public class CandidatMapper implements Mapper<Candidat, CandidatDTO> {
    @Override
    public CandidatDTO entityToDTO(Candidat e) {
        return CandidatDTO.builder()
                .id(e.getId())
                .age(e.getAge())
                //.concours(e.getConcours())
                .build();
    }

    @Override
    public Candidat dtoToEntity(CandidatDTO dto) {
        return Candidat.builder()
                .id(dto.getId())
                .age(dto.getAge())
                //.concours(dto.getConcours())
                .build();
    }
}
