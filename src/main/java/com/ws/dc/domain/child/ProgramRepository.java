package com.ws.dc.domain.child;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    // Custom queries can be added here
}
