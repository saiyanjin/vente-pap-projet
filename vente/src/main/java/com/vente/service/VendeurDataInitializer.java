package com.vente.service;

import com.vente.model.Vendeur;
import com.vente.repository.VendeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class VendeurDataInitializer implements CommandLineRunner {

    @Autowired
    private VendeurRepository vendeurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Utilise le password encoder pour encoder les mots de passe

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si la base de données contient déjà des utilisateurs
        if (vendeurRepository.count() == 0) {
            // Créer des utilisateurs par défaut
            Vendeur vendeur1 = new Vendeur();
            vendeur1.setLogin("leo");
            vendeur1.setPassword(passwordEncoder.encode("leo")); // Encodage du mot de passe
            vendeur1.setRoles(Arrays.asList("USER")); // Exemple de rôle
            vendeur1.setVille("leoVille");

            Vendeur vendeur2 = new Vendeur();
            vendeur2.setLogin("admin");
            vendeur2.setPassword(passwordEncoder.encode("adminpassword")); // Encodage du mot de passe
            vendeur2.setRoles(Arrays.asList("ADMIN")); // Exemple de rôle
            vendeur2.setVille("paris");

            // Sauvegarder les utilisateurs dans la base de données
            vendeurRepository.save(vendeur1);
            vendeurRepository.save(vendeur2);
        }
    }
}
