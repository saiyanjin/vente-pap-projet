package com.vente.service;

import com.vente.model.Vendeur;
import com.vente.repository.VendeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendeurDetailsService implements UserDetailsService {

    @Autowired
    private VendeurRepository vendeurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Vendeur vendeur = vendeurRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        // Ajout du rôle unique en tant que SimpleGrantedAuthority
        return new User(
                vendeur.getLogin(),
                vendeur.getPassword(),
                List.of(new SimpleGrantedAuthority(vendeur.getRole())) // Utilisation d'un seul rôle
        );
    }
}

