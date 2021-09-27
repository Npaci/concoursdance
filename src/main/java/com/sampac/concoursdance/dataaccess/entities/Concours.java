package com.sampac.concoursdance.dataaccess.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Concours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String theme;
    private String description;
    private Date date;

//    @ManyToMany
//    private List<Jury> juges;
//    @ManyToMany
//    private List<Candidat> participants;
}
