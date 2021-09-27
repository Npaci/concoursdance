package com.sampac.concoursdance.metier.mappers;

import com.sampac.concoursdance.dataaccess.entities.Jury;
import com.sampac.concoursdance.metier.dto.JuryDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class JuryMapper implements Mapper<Jury, JuryDTO> {
    @Autowired
    private ConcoursMapper concoursMapper;
    @Override
    public JuryDTO entityToDTO(Jury jury) {
        return JuryDTO.builder()
                .id(jury.getId_Jury())
                .nom(jury.getNom())
                .expertise(jury.getExpertise())
                //Concours
                .concours(
                        jury.getCompetitions()
                                .stream()
                                .map(concoursMapper::entityToSmall)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public Jury dtoToEntity(JuryDTO dto) {
        return Jury.builder()
                .id_Jury(dto.getId())
                .nom(dto.getNom())
                .expertise(dto.getExpertise())
                //Concours
                /*.competitions(
                        dto.getConcours()
                                .stream()
                                .map(concoursMapper::dtoToEntity)
                                .collect(Collectors.toList())
                )*/
                .build();
    }
}
