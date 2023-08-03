package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")

    private Long id;
    private String dni;
    private String name;
    private String lastname;



    //CONSTRUCTOR VACIO
    public Client() {
//SE PIDE CONSTRUCTOR VACIO PARA CREAR NUEVO CLIENTE
    }
    //CONSTRUCTOR
    public Client(String dni, String name, String lastname, Long id) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.id = id;
    }

    //GETTERS Y SETTERS

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }


}
