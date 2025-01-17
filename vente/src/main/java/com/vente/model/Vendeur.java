package com.vente.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Vendeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String login;
    private String password;
    private String ville;
    
    @OneToMany(mappedBy = "vendeur", cascade = CascadeType.ALL)
    private List<Objet> objets = new ArrayList<>();


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}