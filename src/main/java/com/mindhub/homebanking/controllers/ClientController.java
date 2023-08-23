package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;



    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClientId(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(ClientDTO::new)
                .orElseThrow((null) );



    }
    @RequestMapping(path= "/clients/current", method = RequestMethod.GET)
    public ClientDTO getCurrentClient(Authentication authentication){
        ClientDTO  clientDTO1= new ClientDTO(clientRepository.findByEmail(authentication.getName()));
        return clientDTO1;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientRepository.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }

        clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }





}
