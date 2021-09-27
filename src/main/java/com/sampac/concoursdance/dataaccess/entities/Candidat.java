package com.sampac.concoursdance.dataaccess.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Candidat;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private int age;

    @ManyToMany(mappedBy = "candidats", fetch = FetchType.EAGER)
    private List<Concours> compet;
}
