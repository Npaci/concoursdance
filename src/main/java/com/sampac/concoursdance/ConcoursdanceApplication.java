package com.sampac.concoursdance;

import com.sampac.concoursdance.presentation.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ConcoursdanceApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ConcoursdanceApplication.class, args);
		Menu menu = ctx.getBean(Menu.class);
		menu.start();
	}

}
