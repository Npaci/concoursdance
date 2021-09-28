package com.sampac.concoursdance.presentation;

import com.sampac.concoursdance.dataaccess.entities.Jury;
import com.sampac.concoursdance.exceptions.ElementNotFoundException;
import com.sampac.concoursdance.metier.dto.CandidatDTO;
import com.sampac.concoursdance.metier.dto.ConcoursDTO;
import com.sampac.concoursdance.metier.dto.JuryDTO;
import com.sampac.concoursdance.metier.services.CandidatServiceImpl;
import com.sampac.concoursdance.metier.services.ConcoursServiceImpl;
import com.sampac.concoursdance.metier.services.JuryServiceImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Menu {
    private final int NB_MAX_JURY = 3;
    private Scanner scan;
    private ConcoursServiceImpl con_service;
    private CandidatServiceImpl can_service;
    private JuryServiceImpl jur_service;

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
            try {
                displayMenu();
                System.out.print("=> ");
                choix = Integer.parseInt(scan.nextLine());
                mapChoix(choix);
            } catch (NumberFormatException ex) {
                System.out.println(Color.ANSI_RED.val+"Saisie invalide, veuillez entrez un nombre"+Color.ANSI_RESET.val);
            }
        }while (choix != 6);
    }
    private void displayMenu() {
        System.out.println("""
                --- MENU Concour dance ---
                1 - Créer un concour
                2 - Lister un concour
                3 - Détail d'un concour
                4 - Modifier un concour
                5 - Touver un candidat
                6 - quitter """);
    }

    private void mapChoix(int choix){
        switch (choix){
            case 1 -> createConcours();
            case 2 -> displayAll();
            case 3 -> detailConcours();
            case 4 -> quit();
            case 5 -> quit();
            case 6 -> quit();
            default -> System.out.println("choix invalide");
        }
    }
    private void afficheJury(List<JuryDTO> jurys){
        System.out.println(Color.ANSI_PURPLE.val+"Liste des juries".toUpperCase()+Color.ANSI_RESET.val);
        for (JuryDTO juryDTO : jurys) {
            System.out.format("| %d | %s | %s |\n",
                    juryDTO.getId(),
                    juryDTO.getNom(),
                    juryDTO.getExpertise());
        }
    }

//    private void afficheCandidat(List<CandidatDTO> candidats) {
//        System.out.println(Color.ANSI_PURPLE.val+"Liste des juries".toUpperCase()+Color.ANSI_RESET.val);
//        for (JuryDTO juryDTO : jurys) {
//            System.out.format("| %d | %s | %s |\n",
//                    juryDTO.getId(),
//                    juryDTO.getNom(),
//                    juryDTO.getExpertise());
//        }
//    }

    private List<JuryDTO> choixJury(){
        int choix, nbJury=0;
        System.out.println(Color.ANSI_GREEN.val+"Choisissez le(s) jury(s): ".toUpperCase()+Color.ANSI_RESET.val);
        List<JuryDTO> juryDTOS = jur_service.getAll();
        List<JuryDTO> selectedJury = new ArrayList<>();

        do {

            try {

                afficheJury(juryDTOS);
                System.out.print("=> ");
                choix = Integer.parseInt(scan.nextLine());
                if (choix > 0 && choix <= juryDTOS.size()) {
                    selectedJury.add(juryDTOS.remove(choix - 1));
                    nbJury++;
                } else
                    System.out.println(Color.ANSI_RED.val + "Saisie invalide, veuillez choisir un jury existant" + Color.ANSI_RESET.val);

            } catch (NumberFormatException ex) {
                System.out.println(Color.ANSI_RED.val+"Saisie invalide, veuillez entrez un nombre"+Color.ANSI_RESET.val);
            }

        } while (nbJury < NB_MAX_JURY);

        return selectedJury;
    }

//    private List<CandidatDTO> choixCandidat(){
//        int choix, nbJury=0;
//        System.out.println(Color.ANSI_GREEN.val+"Choisissez le(s) jury(s): ".toUpperCase()+Color.ANSI_RESET.val);
//        List<JuryDTO> juryDTOS = jur_service.getAll();
//        List<JuryDTO> selectedJury = new ArrayList<>();
//
//        do {
//
//            try {
//
//                afficheJury(juryDTOS);
//                System.out.print("=> ");
//                choix = Integer.parseInt(scan.nextLine());
//                if (choix > 0 && choix <= juryDTOS.size()) {
//                    selectedJury.add(juryDTOS.remove(choix - 1));
//                    nbJury++;
//                } else
//                    System.out.println(Color.ANSI_RED.val + "Saisie invalide, veuillez choisir un jury existant" + Color.ANSI_RESET.val);
//
//            } catch (NumberFormatException ex) {
//                System.out.println(Color.ANSI_RED.val+"Saisie invalide, veuillez entrez un nombre"+Color.ANSI_RESET.val);
//            }
//
//        } while (nbJury < NB_MAX_JURY);
//
//        return selectedJury;
//    }

    private void createConcours() {
        List<JuryDTO> selectedJury = choixJury();

    }

    private void displayAll(){
        con_service.getAll()
                .forEach( System.out::println );
    }

    private void detailConcours() {
        System.out.println(Color.ANSI_GREEN.val+"veuiller donner id du concours: ".toUpperCase()+Color.ANSI_RESET.val);
        long id = Long.parseLong(scan.nextLine());
        try {
            ConcoursDTO concoursDTO=con_service.getByID(id);
            System.out.println(Color.ANSI_CYAN.val+"Détail du concours de "+concoursDTO.getTheme().toUpperCase());
            System.out.println("Date du concours: "+concoursDTO.getDate());
            System.out.println("Information: "+concoursDTO.getDescription()+Color.ANSI_RESET.val);

            afficheJury(concoursDTO.getJuges());

            System.out.println(Color.ANSI_BLUE.val+"Liste des Participants ".toUpperCase()+Color.ANSI_RESET.val);
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

    private void quit(){
        System.out.println("au revoir!");
    }
}
