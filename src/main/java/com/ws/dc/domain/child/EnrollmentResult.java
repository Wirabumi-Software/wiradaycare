package com.ws.dc.domain.child;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Simple data structure representing the result of an enrollment operation
 * Contains all entities that need to be persisted
 */
@Getter
@AllArgsConstructor
public class EnrollmentResult {
    private final Enrollment enrollment;
    private final Child child;
    private final ClassRoom classRoom;
}