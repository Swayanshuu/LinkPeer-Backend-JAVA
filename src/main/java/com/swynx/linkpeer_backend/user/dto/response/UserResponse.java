package com.swynx.linkpeer_backend.user.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String photoUrl;
    private String role;
    private String branch;
    private String department;
    private String userType;
    private Integer graduatingYear;
    private Boolean profileCompleted;

    // Additional profile fields
    private String college;
    private String stream;
    private String designation;
    private String phone;
    private String github;
    private String link2;
    private String description;

    // Verification
    private Boolean isVerified;
    private Boolean facultyVerified;
    private String facultyVerificationImage;

    // Subscription
    private String subscriptionPlan;
    private String subscriptionStatus;

    // Timestamps
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
}
