package com.ws.dc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthConverter = jwtAuthenticationConverter();

        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(
                    "/auth/login",
                    "/actuator/health",
                    "/",
                    "/index.html",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable())
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<org.springframework.security.core.GrantedAuthority> authorities = new ArrayList<>();
            
            // Extract roles from realm_access.roles (Keycloak structure)
            Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
            if (realmAccess != null && realmAccess.containsKey("roles")) {
                @SuppressWarnings("unchecked")
                List<String> roles = (List<String>) realmAccess.get("roles");
                for (String role : roles) {
                    authorities.add(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + role));
                }
            }
            
            return authorities;
        });
        return jwtConverter;
    }
}
