package com.sampac.concoursdance.metier.dto;

import com.sampac.concoursdance.dataaccess.entities.Concours;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JuryDTO {
    private long id;
    private String nom;
    private String expertise;
    List<ConcoursDTOSmall> concours;
}
