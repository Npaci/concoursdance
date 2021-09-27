package com.sampac.concoursdance.metier.dto;

import com.sampac.concoursdance.dataaccess.entities.Candidat;
import com.sampac.concoursdance.dataaccess.entities.Jury;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ConcoursDTO {
    private long id;
    private String theme;
    private String description;
    private Date date;
//    private List<Jury> juges;
//    private List<Candidat> participants;
}
