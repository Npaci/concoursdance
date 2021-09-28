package com.sampac.concoursdance.presentation;

import com.sampac.concoursdance.exceptions.ElementNotFoundException;
import com.sampac.concoursdance.metier.dto.CandidatDTO;
import com.sampac.concoursdance.metier.dto.ConcoursDTO;
import com.sampac.concoursdance.metier.dto.JuryDTO;
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
        int choix = 0;
        do{
            displayMenu();
            choix = Integer.parseInt(scan.nextLine());
            mapChoix(choix);
        }while (choix != 6);
    }
    private void displayMenu(){
        System.out.println("""
                --- MENU Concour dance ---
                1 - Créer un concour
                2 - Lister un concour
                3 - Détail d'un concour
                4 - Modifier un concour
                5 - Touver un candidat
                6 - quitter
                """);
    }

    private void mapChoix(int choix){
        switch (choix){
            case 1 -> quit();
            case 2 -> displayAll();
            case 3 -> detailConcours();
            case 4 -> quit();
            case 5 -> quit();
            case 6 -> quit();
            default -> System.out.println("choix invalide");
        }
    }

    private void detailConcours() {
        System.out.println(Color.ANSI_GREEN.val+"veuiller donner id du concours: ".toUpperCase()+Color.ANSI_RESET.val);
        long id = Long.parseLong(scan.nextLine());
        try {
            ConcoursDTO concoursDTO=con_service.getByID(id);
            System.out.println(Color.ANSI_CYAN.val+"Détail du concours de "+concoursDTO.getTheme().toUpperCase());
            System.out.println("Date du concours: "+concoursDTO.getDate());
            System.out.println("Information: "+concoursDTO.getDescription()+Color.ANSI_RESET.val);

            System.out.println(Color.ANSI_PURPLE.val+"Liste des juries".toUpperCase()+Color.ANSI_RESET.val);
            for (JuryDTO juryDTO : concoursDTO.getJuges()) {
                System.out.format("| %d | %s | %s |\n",
                        juryDTO.getId(),
                        juryDTO.getNom(),
                        juryDTO.getExpertise());
            }
            System.out.println(Color.ANSI_BLUE.val+"Liste des Participant ".toUpperCase()+Color.ANSI_RESET.val);
            for (CandidatDTO candidatDTO : concoursDTO.getParticipants()) {
                System.out.format("| %d | %s | %d ans |\n",
                        candidatDTO.getId(),
                        candidatDTO.getNom(),
                        candidatDTO.getAge());
            }

        } catch ( ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayAll(){
        con_service.getAll()
                .forEach( System.out::println );
    }
    private void quit(){
        System.out.println("au revoir!");
    }
}
