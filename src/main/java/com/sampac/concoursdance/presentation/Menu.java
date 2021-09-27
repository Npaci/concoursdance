package com.sampac.concoursdance.presentation;

import com.sampac.concoursdance.metier.services.CandidatServiceImpl;
import com.sampac.concoursdance.metier.services.ConcoursServiceImpl;
import com.sampac.concoursdance.metier.services.JuryServiceImpl;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    Scanner scan;
    ConcoursServiceImpl con_service;
    CandidatServiceImpl can_service;
    JuryServiceImpl jur_service;

    public Menu(Scanner scan, ConcoursServiceImpl con_service, CandidatServiceImpl can_service, JuryServiceImpl jur_service) {
        this.scan = scan;
        this.con_service = con_service;
        this.can_service = can_service;
        this.jur_service = jur_service;
    }

    public void start() {
        System.out.println("==========================================");
        System.out.println("============= Menu Principal =============");
        System.out.println("==========================================");
    }
}
