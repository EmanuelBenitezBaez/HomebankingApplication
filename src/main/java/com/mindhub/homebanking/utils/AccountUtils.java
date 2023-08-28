package com.mindhub.homebanking.utils;

import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.CardRepository;

import java.util.Random;

public final class AccountUtils {

    public static String generateRandomAccountNumber() {
        int number = new Random().nextInt(100000000); // Genera un número aleatorio entre 0 y 99999999
        return "VIN-" + String.format("%03d", number); // Formatea el número para tener 8 dígitos
    }

    public static String accountNumber(AccountRepository accountRepository) {
        String number;
        boolean verifyNumber;
        while (true) {
            number = generateRandomAccountNumber();
            verifyNumber = accountRepository.existsByNumber(number);
            if (!verifyNumber) {
                break;
            }
        }
        return number;
    }

    public static String generateNumberCardsRandoms() {
        int number1_1 = new Random().nextInt(2) + 4; // Genera un número aleatorio entre 4 y 5
        int number1 = new Random().nextInt(900) + 100; // Genera un número aleatorio entre 100 y 999
        int number2 = new Random().nextInt(9000) + 1000; // Genera un número aleatorio entre 1000 y 9999
        int number3 = new Random().nextInt(9000) + 1000; // Genera un número aleatorio entre 1000 y 9999
        int number4 = new Random().nextInt(9000) + 1000; // Genera un número aleatorio entre 1000 y 9999
        return number1_1 + "" + number1 + "-" + number2 + "-" + number3 + "-" + number4;
    }

    public static String numberCards(CardRepository cardRepo) {
        String number;
        boolean verifyNumber;
        while (true) {
            number = generateNumberCardsRandoms();
            verifyNumber = cardRepo.existsByNumber(number);
            if (!verifyNumber) {
                break;
            }
        }
        return number;
    }

    // ...


    public static int generateRandomCVV() {
        Random random = new Random();
        int cvv = random.nextInt(900) + 100; // Genera un número aleatorio de 3 dígitos (entre 100 y 999)
        return cvv;
    }
}
