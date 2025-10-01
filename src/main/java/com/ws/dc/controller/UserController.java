package com.ws.dc.controller;

import com.ws.dc.domain.user.User;
import com.ws.dc.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    // Get users by role - useful for finding parents, caregivers, etc.
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    @GetMapping("/by-role/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return userRepository.findByRole(com.ws.dc.domain.user.Role.valueOf(role.toUpperCase()));
    }

    // Get user by external ID (Keycloak UUID) - useful for integration
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/external/{externalId}")
    public User getUserByExternalId(@PathVariable String externalId) {
        return userRepository.findByExternalId(externalId).orElse(null);
    }
}