package com.ws.dc.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalId; // Keycloak user UUID
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
