package com.sampac.concoursdance.metier.dto;

import com.sampac.concoursdance.dataaccess.entities.Candidat;
import com.sampac.concoursdance.dataaccess.entities.Jury;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcoursDTO {
    private long id;
    private String theme;
    private String description;
    private Date date;
    private List<JuryDTO> juges;
    private List<CandidatDTO> participants;
}
