package com.sampac.concoursdance.presentation;

import com.sampac.concoursdance.dataaccess.entities.Concours;
import com.sampac.concoursdance.dataaccess.entities.Jury;
import com.sampac.concoursdance.exceptions.AlreadyExistException;
import com.sampac.concoursdance.exceptions.ElementNotFoundException;
import com.sampac.concoursdance.metier.dto.CandidatDTO;
import com.sampac.concoursdance.metier.dto.ConcoursDTO;
import com.sampac.concoursdance.metier.dto.ConcoursDTOSmall;
import com.sampac.concoursdance.metier.dto.JuryDTO;
import com.sampac.concoursdance.metier.services.CandidatServiceImpl;
import com.sampac.concoursdance.metier.services.ConcoursServiceImpl;
import com.sampac.concoursdance.metier.services.JuryServiceImpl;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        do {
            try {
                displayMenu();
                System.out.print("=> ");
                choix = Integer.parseInt(scan.nextLine());
                mapChoix(choix);
            } catch (NumberFormatException ex) {
                System.out.println(Color.ANSI_RED.val + "Saisie invalide, veuillez entrez un nombre" + Color.ANSI_RESET.val);
            }
        } while (choix != 6);
    }

    private void displayMenu() {
        System.out.println(Color.ANSI_PURPLE.val
                +"""
                --- MENU DANCE ---\n"""
                +Color.ANSI_RESET.val
                +"""
                1 - Créer un concour
                2 - Lister un concour
                3 - Détail d'un concour
                4 - Modifier un concour
                5 - Touver un candidat
                6 - quitter """);
    }

    private void mapChoix(int choix) {
        switch (choix) {
            case 1 -> createConcours();
            case 2 -> displayAll();
            case 3 -> detailConcours();
            case 4 -> modifConcours();
            case 5 -> findCandidat();
            case 6 -> quit();
            default -> System.out.println("choix invalide");
        }
    }

    private void modifConcours() {

        try {

            System.out.println(Color.ANSI_GREEN.val+"veuiller donner id du concours: ".toUpperCase()+Color.ANSI_RESET.val);
            long id = Long.parseLong(scan.nextLine());

            ConcoursDTO concoursDTO=con_service.getByID(id);
            ConcoursDTO.ConcoursDTOBuilder concoursDTOnew=initValueConcour();
            concoursDTOnew.juges(concoursDTO.getJuges());
            concoursDTOnew.participants(concoursDTO.getParticipants());
            concoursDTOnew.build();
            con_service.update(concoursDTOnew.build());

        }
        catch ( ElementNotFoundException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    private ConcoursDTO.ConcoursDTOBuilder initValueConcour() {
        ConcoursDTO.ConcoursDTOBuilder concoursDTO=ConcoursDTO.builder();
        System.out.println(Color.ANSI_GREEN.val+"Entrez le thème".toUpperCase()+Color.ANSI_RESET.val);
        concoursDTO.theme(scan.nextLine());
        System.out.println(Color.ANSI_GREEN.val+"Entrez la description".toUpperCase()+Color.ANSI_RESET.val);
        concoursDTO.description(scan.nextLine());
        System.out.println(Color.ANSI_GREEN.val+"Entrez la date".toUpperCase()+Color.ANSI_RESET.val);
        String dateFormat = "yyyy-MM-dd";
        try {
            concoursDTO.description(String.valueOf(new SimpleDateFormat(dateFormat).parse(scan.nextLine())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        concoursDTO.build();
        return concoursDTO;
    }
    private void findCandidat() {
        System.out.println(Color.ANSI_GREEN.val+"veuiller donner id du candidat: ".toUpperCase()+Color.ANSI_RESET.val);
        long id = Long.parseLong(scan.nextLine());
        try {
            CandidatDTO candidatDTO=can_service.getByID(id);
            System.out.format("| %d | %s | %d ans |\n",
                    candidatDTO.getId(),
                    candidatDTO.getNom(),
                    candidatDTO.getAge());
            afficheConcoursSmall(candidatDTO.getConcours(),candidatDTO.getNom());

        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void afficheJury(List<JuryDTO> jurys) {
        System.out.println(Color.ANSI_PURPLE.val + "Liste des juries".toUpperCase() + Color.ANSI_RESET.val);
        int i = 1;
        for (JuryDTO juryDTO : jurys) {
            System.out.format("| %d | %s | %s |\n",
                    i,
                    juryDTO.getNom(),
                    juryDTO.getExpertise());
            i++;
        }
    }

    private void afficheCandidat(List<CandidatDTO> candidats) {
        System.out.println(Color.ANSI_BLUE.val+"Liste des Participants ".toUpperCase()+Color.ANSI_RESET.val);
        int i =1;
        for (CandidatDTO candidatDTO : candidats) {
            System.out.format("| %d | %s | %d ans |\n",
                    i,
                    candidatDTO.getNom(),
                    candidatDTO.getAge());
            i++;
        }
    }
    private void afficheConcoursSmall(List<ConcoursDTOSmall> concours,String nom) {
        System.out.println(Color.ANSI_BLUE.val+("Liste des Concours où "+nom+" Participe").toUpperCase()+Color.ANSI_RESET.val);
        for (ConcoursDTOSmall concoursDTO : concours) {
            System.out.format("| %d | %s | %s | %s |\n",
                    concoursDTO.getId(),
                    concoursDTO.getTheme(),
                    concoursDTO.getDate(),
                    concoursDTO.getDescription());
        }
    }
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

    private List<CandidatDTO> choixCandidat(){
        int choix;
        char yesNon = 'y';
        System.out.println(Color.ANSI_GREEN.val+"Choix de Candidat(e)s: ".toUpperCase()+Color.ANSI_RESET.val);
        List<CandidatDTO> candidatDTOS = can_service.getAll();
        List<CandidatDTO> selectedCandi = new ArrayList<>();

        do {

            try {

                afficheCandidat(candidatDTOS);
                System.out.print("=> ");
                choix = Integer.parseInt(scan.nextLine());
                if (choix > 0 && choix <= candidatDTOS.size()) {
                    selectedCandi.add(candidatDTOS.remove(choix - 1));

                    if (candidatDTOS.size() > 0) {
                        System.out.println("Voulez-vous ajouter un autre candidat ? (y/n)");
                        yesNon = scan.nextLine().charAt(0);
                    }
                }
                else
                    System.out.println(Color.ANSI_RED.val + "Saisie invalide, veuillez choisir un candidat existant" + Color.ANSI_RESET.val);

            } catch (NumberFormatException ex) {
                System.out.println(Color.ANSI_RED.val+"Saisie invalide, veuillez entrez un nombre"+Color.ANSI_RESET.val);
            }

        } while ((yesNon == 'y' || yesNon == 'Y') && candidatDTOS.size() > 0);

        return selectedCandi;
    }

    private void createConcours() {
        List<JuryDTO> selectedJury = choixJury();
        List<CandidatDTO> selectedCand = choixCandidat();

        ConcoursDTO.ConcoursDTOBuilder conBuilder = initValueConcour();
        conBuilder.juges(selectedJury);
        conBuilder.participants(selectedCand);

        try {
            con_service.insert(conBuilder.build());
        } catch (AlreadyExistException e) {
            System.out.println(e.getMessage());
        }

    }

    private void displayAll(){
       List<ConcoursDTO>list= con_service.getAll();
        System.out.println(Color.ANSI_BLUE.val+("Liste des Concours".toUpperCase()+Color.ANSI_RESET.val));

        for (ConcoursDTO concoursDTO : list) {
            System.out.format("| %d | %s | %s | %s |\n",
                    concoursDTO.getId(),
                    concoursDTO.getTheme(),
                    concoursDTO.getDate(),
                    concoursDTO.getDescription());
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

            afficheJury(concoursDTO.getJuges());
            afficheCandidat(concoursDTO.getParticipants());

        } catch ( ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void quit(){
        System.out.println("au revoir!");
    }
}
