package ch.heg.ig.BachEtBuck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(BackEtBuckRestaurantRuntimeHints.class)
public class BackEtBuckRestaurant {

	public static void main(String[] args) {
		SpringApplication.run(BackEtBuckRestaurant.class, args);
	}

}
