package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.mindhub.homebanking.utils.AccountUtils.numberCards;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client/current/card")
    public List<CardDTO> listcards(Authentication authentication){
        Client client=clientRepository.findByEmail(authentication.getName());
        return client.getCards().stream().filter(active->active.getState()== State.ACTIVE).map(CardDTO::new).collect(toList());
    }

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> newCard(
            Authentication authentication,
            @RequestParam CardType cardType,
            @RequestParam CardColor cardColor){
        Client client=clientRepository.findByEmail(authentication.getName());
        if(client.getCards().stream().filter(card -> card.getType()==cardType).collect(toSet()).size()>=3){
            return new ResponseEntity<>("You have already reached the limit of 3 "+cardType+" cards, you cannot be given another one", HttpStatus.BAD_REQUEST);
        }
        if (clientRepository.findByEmail(authentication.getName()).getCards().stream().anyMatch(card -> card.getColor()==cardColor&&card.getType()==cardType)){
            return new ResponseEntity<>("you have already the same card", HttpStatus.BAD_REQUEST);
        }
        Card card = new Card(client.getFirstName()+" "+client.getLastName(),cardType,cardColor,numberCards(cardRepository), LocalDate.now(),LocalDate.now().plusYears(5),client);
        clientRepository.findByEmail(authentication.getName()).addCard(card);
        cardRepository.save(card);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }






}