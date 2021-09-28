package com.sampac.concoursdance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfig {
    @Bean
    public Scanner scanner() {
        System.out.println("Instanciation de Scanner");
        return new Scanner(System.in);
    }
}
