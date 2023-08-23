package com.mindhub.homebanking.repositories;


import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Long> {
//En la interfaz se definen las funciones y con otras clases se implementan
//Solo puedo definir encabezados de funciones, sin textos.
Client findByEmail (String email); //Defini una funcion que voy a querer tener. Buscar por email.

}
