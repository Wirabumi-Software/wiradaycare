package com.ws.dc.domain.child;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    // Custom queries can be added here
}
