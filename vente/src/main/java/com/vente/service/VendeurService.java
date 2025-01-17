package com.vente.service;

import com.vente.model.Vendeur;
import com.vente.repository.VendeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VendeurService {

    @Autowired
    private VendeurRepository vendeurRepository;

    public void inscrireVendeur(Vendeur vendeur) {
        // Vérifier si le login existe déjà
        if (vendeurRepository.findByLogin(vendeur.getLogin()).isPresent()) {
            throw new IllegalArgumentException("Ce login est déjà pris");
        }
        vendeurRepository.save(vendeur);
    }
    
    public Vendeur trouverParLogin(String login) {
        return vendeurRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Vendeur non trouvé : " + login));
    }
}
