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
            case 3 -> quit();
            case 4 -> quit();
            case 5 -> quit();
            case 6 -> quit();
            default -> System.out.println("choix invalide");
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
