package com.sampac.concoursdance.configuration;

import com.sampac.concoursdance.metier.services.CandidatServiceImpl;
import com.sampac.concoursdance.metier.services.ConcoursServiceImpl;
import com.sampac.concoursdance.metier.services.JuryServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFiller implements InitializingBean {
    ConcoursServiceImpl con_service;
    CandidatServiceImpl can_service;
    JuryServiceImpl jur_service;

    public DatabaseFiller(ConcoursServiceImpl con_service, CandidatServiceImpl can_service, JuryServiceImpl jur_service) {
        this.con_service = con_service;
        this.can_service = can_service;
        this.jur_service = jur_service;
    }

    private void creerCandidats() {
//        con_service.insert();
//        con_service.insert();
//        con_service.insert();
    }

    private void creerConcours() {
//        can_service.insert();
//        can_service.insert();
//        can_service.insert();
    }

    private void creerJuries() {
//        jur_service.insert();
//        jur_service.insert();
//        jur_service.insert();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("La DB va être polpulée");
        creerCandidats();
        creerJuries();
        creerConcours();
    }
}
