package com.sampac.concoursdance.metier.dto;

import com.sampac.concoursdance.dataaccess.entities.Concours;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CandidatDTO {
    private long id;
    private String nom;
    private int age;
//    private List<Concours> concours;
}
