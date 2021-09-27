package com.sampac.concoursdance.configuration;

import com.sampac.concoursdance.presentation.Menu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfig {
    @Bean
    public Scanner scanner(){
        System.out.println("instanciation de scanner");
        return new Scanner(System.in);
    }
}
