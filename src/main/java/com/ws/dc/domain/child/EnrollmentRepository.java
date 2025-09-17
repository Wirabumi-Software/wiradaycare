package com.ws.dc.domain.child;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Custom queries can be added here
}
