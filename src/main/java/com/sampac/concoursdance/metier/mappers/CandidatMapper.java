package com.sampac.concoursdance.metier.mappers;

import com.sampac.concoursdance.dataaccess.entities.Candidat;
import com.sampac.concoursdance.dataaccess.entities.Concours;
import com.sampac.concoursdance.metier.dto.CandidatDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class CandidatMapper implements Mapper<Candidat, CandidatDTO> {
    @Autowired
    private ConcoursMapper concoursMapper;
    @Override
    public CandidatDTO entityToDTO(Candidat candidat) {
        return CandidatDTO.builder()
                .id(candidat.getId_Candidat())
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
                .age(dto.getAge())
                //Concours
               /* .compet(
                        dto.getConcours()
                                .stream()
                                .map(concoursMapper::dtoToEntity)
                                .collect(Collectors.toList())
                )*/
                .build();
    }
}
