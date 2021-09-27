package com.sampac.concoursdance.metier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ConcoursDTOSmall {
    private long id;
    private String theme;
    private String description;
    private Date date;
}
