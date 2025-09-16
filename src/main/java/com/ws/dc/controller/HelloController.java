package com.ws.dc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, authenticated user!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Hello, ADMIN!";
    }

    @GetMapping("/caregiver")
    @PreAuthorize("hasRole('CAREGIVER')")
    public String caregiver() {
        return "Hello, CAREGIVER!";
    }

    @GetMapping("/parent")
    @PreAuthorize("hasRole('PARENT')")
    public String parent() {
        return "Hello, PARENT!";
    }
}
