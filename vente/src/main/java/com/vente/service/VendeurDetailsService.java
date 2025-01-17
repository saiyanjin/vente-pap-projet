package com.vente.service;

import com.vente.model.Vendeur;
import com.vente.repository.VendeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VendeurDetailsService implements UserDetailsService {

    @Autowired
    private VendeurRepository vendeurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Vendeur vendeur = vendeurRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Vendeur not found: " + username));

        // Convertissez le vendeur en un utilisateur de Spring Security
        return User.builder()
                .username(vendeur.getLogin())
                .password(vendeur.getPassword())
                .authorities("USER") // Ajoutez des rôles si nécessaire
                .build();
    }
}
