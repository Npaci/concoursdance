package com.sampac.concoursdance.configuration;

import com.sampac.concoursdance.dataaccess.entities.Candidat;
import com.sampac.concoursdance.dataaccess.entities.Concours;
import com.sampac.concoursdance.dataaccess.entities.Jury;
import com.sampac.concoursdance.exceptions.AlreadyExistException;
import com.sampac.concoursdance.metier.dto.CandidatDTO;
import com.sampac.concoursdance.metier.dto.ConcoursDTO;
import com.sampac.concoursdance.metier.dto.JuryDTO;
import com.sampac.concoursdance.metier.mappers.ConcoursMapper;
import com.sampac.concoursdance.metier.services.CandidatServiceImpl;
import com.sampac.concoursdance.metier.services.ConcoursServiceImpl;
import com.sampac.concoursdance.metier.services.JuryServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseFiller implements InitializingBean {
    ConcoursServiceImpl con_service;
    CandidatServiceImpl can_service;
    JuryServiceImpl jur_service;

    List<ConcoursDTO> concoursList = new ArrayList<>();
    List<CandidatDTO> candidatList = new ArrayList<>();
    List<JuryDTO> juryList = new ArrayList<>();


    public DatabaseFiller(ConcoursServiceImpl con_service, CandidatServiceImpl can_service, JuryServiceImpl jur_service) {
        this.con_service = con_service;
        this.can_service = can_service;
        this.jur_service = jur_service;
    }

    private void creerCandidats() {
        ConcoursMapper concours_mapper = new ConcoursMapper();
        List<Concours> list = concoursList.stream().map(concours_mapper::dtoToEntity).collect(Collectors.toList());
        candidatList.add(CandidatDTO.builder()
                .id(0)
                .nom("Jean")
                .concours(list.stream()
                        .map(concours_mapper::entityToSmall)
                        .collect(Collectors.toList()))
                .build());
        candidatList.add(CandidatDTO.builder()
                .id(0)
                .nom("Marco")
                .concours(list.stream()
                        .map(concours_mapper::entityToSmall)
                        .collect(Collectors.toList()))
                .build());

        try {
            can_service.insert(candidatList.get(0));
            can_service.insert(candidatList.get(1));
        }catch (AlreadyExistException ex){
            System.out.println("Erreur DatabaseFiller creerCandidats() : "+ex.getMessage());
        }
    }

    private void creerConcours() {
        concoursList.add(ConcoursDTO.builder()
                .id(0)
                .theme("Freestyle")
                .description("Blablabla")
                .date(new Date())
                .juges(juryList)
                .build());
        concoursList.add(ConcoursDTO.builder()
                .id(0)
                .theme("Classique")
                .description("Blablabla")
                .date(new Date())
                .juges(juryList)
                .build());

        try {
            con_service.insert(concoursList.get(0));
            con_service.insert(concoursList.get(1));
        }catch (AlreadyExistException ex){
            System.out.println("Erreur DatabaseFiller creerConcours() : "+ex.getMessage());
        }
    }

    private void creerJuries() {
        juryList.add(JuryDTO.builder()
                        .id(0)
                        .nom("Jean")
                        .expertise("Amateur")
                        .build());
        juryList.add(JuryDTO.builder()
                .id(0)
                .nom("Marco")
                .expertise("Pro")
                .build());

        try {
            jur_service.insert(juryList.get(0));
            jur_service.insert(juryList.get(1));
        }catch (AlreadyExistException ex){
            System.out.println("Erreur DatabaseFiller creerJuries() : "+ex.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("La DB va être populée");
        creerJuries();
        creerConcours();
        creerCandidats();

    }
}
