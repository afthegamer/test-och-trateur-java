package fr.esgi.tier_liste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SujetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SujetServiceApplication.class, args);
	}

}
