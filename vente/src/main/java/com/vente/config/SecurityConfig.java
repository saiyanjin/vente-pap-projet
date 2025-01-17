package com.vente.config;

import com.vente.service.VendeurDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final VendeurDetailsService vendeurDetailsService;

    public SecurityConfig(VendeurDetailsService vendeurDetailsService) {
        this.vendeurDetailsService = vendeurDetailsService;
    }

    @SuppressWarnings({ "removal", "deprecation" })
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests(requests -> requests
	            .requestMatchers("/css/**", "/js/**", "/images/**", "/favicon.ico", "/inscription").permitAll()
	            .requestMatchers("/ajouter").hasRole("USER")  // Accès seulement aux utilisateurs authentifiés
	            .anyRequest().authenticated())
	        .formLogin(login -> login.permitAll())  // Permet à tous d'accéder à la page de login
	        .logout(logout -> logout.permitAll());  // Permet à tous de se déconnecter

	    return http.build();
	}


    @Bean
    UserDetailsService userDetailsService() {
        return vendeurDetailsService;
    }
}
