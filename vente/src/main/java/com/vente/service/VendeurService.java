package com.vente.service;

import com.vente.model.Vendeur;
import com.vente.repository.VendeurRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class VendeurService {

    @Autowired
    private VendeurRepository vendeurRepository;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void inscrireVendeur(Vendeur vendeur) {
        // Vérifier si le login existe déjà
        if (vendeurRepository.findByLogin(vendeur.getLogin()).isPresent()) {
            throw new IllegalArgumentException("Ce login est déjà pris");
        }
        vendeur.setPassword(passwordEncoder.encode(vendeur.getPassword()));
        vendeur.setRole("ROLE_USER");
        vendeurRepository.save(vendeur);
    }
    
    public Vendeur trouverParLogin(String login) {
        return vendeurRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Vendeur non trouvé : " + login));
    }
}
