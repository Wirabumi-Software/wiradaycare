package com.ws.dc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
        // Later we can plug in a converter that maps Keycloak roles → Spring roles

        http
            .authorizeHttpRequests(authz -> authz
                // actuator health always public
                .requestMatchers("/actuator/health").permitAll()
                .requestMatchers("/auth/login").permitAll()
                // role-based API security
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/caregiver/**").hasRole("CAREGIVER")
                .requestMatchers("/api/parent/**").hasRole("PARENT")
                // everything else must be authenticated
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
            );

        return http.build();
    }
}
