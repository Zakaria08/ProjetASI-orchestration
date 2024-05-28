package org.springframework.CaisseEnregistreuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackAndbuckAnalytics {

	public static void main(String[] args) {
		SpringApplication.run(BackAndbuckAnalytics.class, args);
		System.out.println("Je suis le front-end de l'application Caisse Enregistreuse.");
	}

}
