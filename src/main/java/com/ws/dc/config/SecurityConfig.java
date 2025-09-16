package com.ws.dc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Public endpoints (login, actuator)
    @Bean
    public SecurityFilterChain publicEndpoints(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/auth/login", "/actuator/health")
            .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
            .csrf(csrf -> csrf.disable());
        return http.build();
    }

    // All other endpoints: require JWT
    @Bean
    public SecurityFilterChain apiEndpoints(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();

        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/caregiver/**").hasRole("CAREGIVER")
                .requestMatchers("/api/parent/**").hasRole("PARENT")
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));

        return http.build();
    }
}
