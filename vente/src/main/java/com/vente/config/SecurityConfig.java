package com.vente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vente.service.VendeurDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final VendeurDetailsService vendeurDetailsService;

    public SecurityConfig(VendeurDetailsService vendeurDetailsService) {
        this.vendeurDetailsService = vendeurDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(vendeurDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/", "/inscription", "/css/**", "/js/**").permitAll()
                .requestMatchers("/rechercher-objets", "/ajouterObjet", "/objets", "/mes-objets").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/chiffre-affaires").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
            )
            .logout(logout -> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))  // Définir l'URL de déconnexion
                    .logoutSuccessUrl("/logout.done")  // Rediriger vers /logout.done après une déconnexion réussie
                    .deleteCookies("JSESSIONID")  // Supprimer le cookie JSESSIONID
                    .invalidateHttpSession(true)  // Invalider la session HTTP
                );

        return http.build();
    }

}
