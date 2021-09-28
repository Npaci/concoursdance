package com.sampac.concoursdance.metier.mappers;

import com.sampac.concoursdance.dataaccess.entities.Candidat;
import com.sampac.concoursdance.dataaccess.entities.Concours;
import com.sampac.concoursdance.dataaccess.entities.Jury;
import com.sampac.concoursdance.metier.dto.CandidatDTO;
import com.sampac.concoursdance.metier.dto.ConcoursDTO;
import com.sampac.concoursdance.metier.dto.ConcoursDTOSmall;
import com.sampac.concoursdance.metier.dto.JuryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ConcoursMapper implements Mapper<Concours, ConcoursDTO> {
    @Autowired
    private Mapper<Jury,JuryDTO> juryMapper;
    @Autowired
    private Mapper<Candidat,CandidatDTO> candidatMapper;
    @Override
    public ConcoursDTO entityToDTO(Concours concours) {
        if(concours == null)
            return null;
        return ConcoursDTO.builder()
                .id(concours.getId_Concour())
                .theme(concours.getTheme())
                .description(concours.getDescription())
                .date(concours.getDate())
                //Candidates
                .participants(
                        concours.getCandidats()
                                .stream()
                                .map(candidatMapper::entityToDTO)
                                .collect(Collectors.toList())
                )
                //Juries
                .juges(
                        concours.getJuries()
                        .stream()
                        .map(juryMapper::entityToDTO)
                        .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public Concours dtoToEntity(ConcoursDTO dto) {
        if(dto == null)
            return null;
        return Concours.builder()
                .id_Concour(dto.getId())
                .theme(dto.getTheme())
                .description(dto.getDescription())
                .date(dto.getDate())
                //Candidates
                .candidats(
                        dto.getParticipants()
                                .stream()
                                .map(candidatMapper::dtoToEntity)
                                .collect(Collectors.toList())
                )
                //Juries
                .juries(
                        dto.getJuges()
                                .stream()
                                .map(juryMapper::dtoToEntity)
                                .collect(Collectors.toList())
                )
                .build();
    }
    public ConcoursDTOSmall entityToSmall(Concours concours){
        if(concours == null)
            return null;

        return ConcoursDTOSmall.builder()
                .id(concours.getId_Concour())
                .theme(concours.getTheme())
                .description(concours.getDescription())
                .date(concours.getDate())
                .build();
    }
    public Concours dtoToEntitySmall(ConcoursDTOSmall dto) {
        if(dto == null)
            return null;
        return Concours.builder()
                .id_Concour(dto.getId())
                .theme(dto.getTheme())
                .description(dto.getDescription())
                .date(dto.getDate())
                .build();
    }
}
