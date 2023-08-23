package com.mindhub.homebanking;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {


	@Autowired
	private PasswordEncoder passwordEncoder; // Inyectar PasswordEncoder
	public static void main(String[] args) {

		SpringApplication.run(HomebankingApplication.class, args);

	}
	@Bean
	public CommandLineRunner initData(ClientRepository ClientRepository, AccountRepository AccountRepository, TransactionRepository transactionRepository,
									  LoanRepository LoanRepository, ClientLoanRepository ClientLoanRepository, CardRepository CardRepository){
		return (args) -> {
			Client client = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("123")); // Aca se cifra la contraseña
			ClientRepository.save(client);

			Client client1 = new Client("Emanuel", "Benitez Baez", "emanuel@gmail.com", passwordEncoder.encode("111")); // Aca se cifra la contraseña
			ClientRepository.save(client1);


			Account account = new Account("VIN001", LocalDate.now(),5000);
			client.addAccount(account);
			AccountRepository.save(account);

			Account account1 = new Account("VIN002", LocalDate.now().plusDays(1),7500);
			client.addAccount(account1);
			AccountRepository.save(account1);

			ClientDTO clientDTO = new ClientDTO(client);

			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 150.000,"Pago Alquiler",LocalDate.now());
			Transaction transaction2 = new Transaction(TransactionType.CREDIT, 100.000,"Compras Supermercado",LocalDate.now());
			Transaction transaction3 = new Transaction(TransactionType.DEBIT, 200.000,"Pago de Impuestos",LocalDate.now());

			account.addTransaction(transaction1);
			account.addTransaction(transaction2);
			account1.addTransaction(transaction3);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);

//
            Loan loan = new Loan("Hipotecario",500000.000, List.of(12,24,36,48,60));
            LoanRepository.save(loan);
            Loan loan1 = new Loan("Personal",100000.000, List.of(6,12,24));
            LoanRepository.save(loan1);
            Loan loan2 = new Loan("Automotriz",300000.000, List.of(6,12,24,36));
            LoanRepository.save(loan2);

			ClientLoan clientLoan = new ClientLoan(400000.00,60,loan,client);
			ClientLoanRepository.save(clientLoan);
			ClientLoan clientLoan1 = new ClientLoan(50000.00,12,loan1,client);
			ClientLoanRepository.save(clientLoan1);


///////

// Crea tarjetas GOLD para Melba
			Card debitCardGold = new Card("Melba Morel",CardType.DEBIT,CardColor.GOLD,"2318-4541-5645-4575","990",LocalDate.now(),LocalDate.now().plusYears(5),client);
			client.addCard(debitCardGold);
			CardRepository.save(debitCardGold);
			Card creditCardGold = new Card("Melba Morel",CardType.CREDIT,CardColor.GOLD,"6587-4541-5645-5775","991",LocalDate.now(),LocalDate.now().plusYears(5),client);
			client.addCard(creditCardGold);
			CardRepository.save(creditCardGold);

// Crea tarjetas  Titanium para Melba
			Card creditCardTitanium = new Card("Melba Morel",CardType.CREDIT,CardColor.TITANIUM,"8744-1115-6454-5865","750",LocalDate.now(),LocalDate.now().plusYears(5),client);
			client.addCard(creditCardTitanium);
			CardRepository.save(creditCardTitanium);
			Card debitCardTitanium = new Card("Melba Morel",CardType.DEBIT,CardColor.TITANIUM,"4458-1115-6454-8565","751",LocalDate.now(),LocalDate.now().plusYears(5),client);
			client.addCard(debitCardTitanium);
			CardRepository.save(debitCardTitanium);

			// Crea tarjetas Silver para Melba
			Card debitCardSilver = new Card("Melba Morel",CardType.DEBIT,CardColor.SILVER,"2235-1115-6454-7465","753",LocalDate.now(),LocalDate.now().plusYears(5),client);
			client.addCard(debitCardSilver);
			CardRepository.save(debitCardSilver);
			Card creditCardSilver = new Card("Melba Morel",CardType.CREDIT,CardColor.SILVER,"1112-1115-6454-9265","754",LocalDate.now(),LocalDate.now().plusYears(5),client);
			client.addCard(creditCardSilver);
			CardRepository.save(creditCardSilver);


// Crear una tarjeta de crédito Silver para Emanuel
			Card cardSilver = new Card("Emanuel Benitez Baez", CardType.CREDIT, CardColor.SILVER, "1235-6242-5321-7452", "453", LocalDate.now(), LocalDate.now().plusYears(5), client1);
			client1.addCard(cardSilver);
			CardRepository.save(cardSilver);




		};
	}

}
