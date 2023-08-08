package com.mindhub.homebanking;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {

		SpringApplication.run(HomebankingApplication.class, args);

	}
	@Bean
	public CommandLineRunner initData(ClientRepository ClientRepository, AccountRepository AccountRepository){
		return (args) -> {
			Client client = new Client( "Melba","Morel","melba@mindhub.com");
			ClientRepository.save(client);

			Client client1 = new Client( "Emanuel","Benitez Baez","emanuel@gmail.com");
			ClientRepository.save(client1);



			Account account = new Account("VIN001", LocalDate.now(),5000);
			client.addAccount(account);
			AccountRepository.save(account);

			Account account1 = new Account("VIN002", LocalDate.now().plusDays(1),7500);
			client.addAccount(account1);
			AccountRepository.save(account1);


			ClientDTO clientDTO = new ClientDTO(client);

			//ClientDTO clientDTO1 = new ClientDTO(client1);

		};
	}

}
