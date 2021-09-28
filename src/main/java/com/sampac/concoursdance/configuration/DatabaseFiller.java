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
        //List<Concours> list = concoursList.stream().map(concours_mapper::dtoToEntity).collect(Collectors.toList());
        candidatList.add(CandidatDTO.builder()
                .id(1)
                .nom("Jean")
                                .age(30)
//                .concours(list.stream()
//                        .map(concours_mapper::entityToSmall)
//                        .collect(Collectors.toList()))
                .concours(new ArrayList<>())
                .build());
        candidatList.add(CandidatDTO.builder()
                .id(2)
                .nom("Marco")
                                .age(25)
//                .concours(list.stream()
//                        .map(concours_mapper::entityToSmall)
//                        .collect(Collectors.toList()))
                        .concours(new ArrayList<>())
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
                .participants(candidatList)
                .build());

        try {
            con_service.insert(concoursList.get(0));
        }catch (AlreadyExistException ex){
            System.out.println("Erreur DatabaseFiller creerConcours() : "+ex.getMessage());
        }
    }

    private void creerJuries() {
        juryList.add(JuryDTO.builder()
                        .id(1)
                        .nom("Maude")
                        .expertise("Danceuse Pro")
                        .build());
        juryList.add(JuryDTO.builder()
                .id(2)
                .nom("Chris")
                .expertise("Danceur Pro")
                .build());
        juryList.add(JuryDTO.builder()
                .id(3)
                .nom("Jean-Marc")
                .expertise("Danceur Pro")
                .build());

        try {
            jur_service.insert(juryList.get(0));
            jur_service.insert(juryList.get(1));
            jur_service.insert(juryList.get(2));
        }catch (AlreadyExistException ex){
            System.out.println("Erreur DatabaseFiller creerJuries() : "+ex.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        creerJuries();
        creerCandidats();
        creerConcours();
        System.out.println("La DB a été populée");
    }
}
