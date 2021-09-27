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
public class Jury {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Jury;
    @Column(nullable = false)
    private String nom;
    private String expertise;

    @ManyToMany(mappedBy = "juries", fetch = FetchType.EAGER)
    private List<Concours> competitions;
}
