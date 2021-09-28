package com.sampac.concoursdance.metier.mappers;

import com.sampac.concoursdance.dataaccess.entities.Candidat;
import com.sampac.concoursdance.dataaccess.entities.Concours;
import com.sampac.concoursdance.metier.dto.CandidatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CandidatMapper implements Mapper<Candidat, CandidatDTO> {
    @Autowired
    private ConcoursMapper concoursMapper;
    @Override
    public CandidatDTO entityToDTO(Candidat candidat) {
        return CandidatDTO.builder()
                .id(candidat.getId_Candidat())
                .nom(candidat.getNom())
                .age(candidat.getAge())
                .concours(
                        candidat.getCompet()
                                .stream()
                                .map(concoursMapper::entityToSmall)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public Candidat dtoToEntity(CandidatDTO dto) {
        return Candidat.builder()
                .id_Candidat(dto.getId())
                .nom(dto.getNom())
                .age(dto.getAge())
                //Concours
                .compet(
                        dto.getConcours()
                                .stream()
                                .map(concoursMapper::dtoToEntitySmall)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
