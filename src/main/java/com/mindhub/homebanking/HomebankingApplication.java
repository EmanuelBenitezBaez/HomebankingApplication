package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomebankingApplication {


	public static void main(String[] args) {

		SpringApplication.run(HomebankingApplication.class, args);


	}
	@Bean
	public CommandLineRunner initData(ClientRepository ClientRepository){
		return (args) -> {
			Client client = new Client("78945661","Emanuel","Bez",null);
			ClientRepository.save(client);
			//
		};
	}

}
