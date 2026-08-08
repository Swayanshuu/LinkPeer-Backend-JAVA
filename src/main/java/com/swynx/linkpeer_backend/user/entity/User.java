package com.swynx.linkpeer_backend.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    // Basic Information
    @Id
    @Column(name="id")
    private String id;

    private String name;
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    // User Information
    private String role;

    private String branch;

    private String college;

    private String stream;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "graduating_year")
    private Integer graduatingYear;

    private String department;

    private String designation;

    private String phone;

    // Social Information
    private String github;

    private String link2;

    private String description;

    // Verification
    @Column(name = "is_verified")
    private Boolean verified;

    @Column(name = "faculty_verified")
    private Boolean facultyVerified;

    @Column(name = "faculty_verification_image")
    private String facultyVerificationImage;

    @Column(name = "faculty_verification_status")
    private String facultyVerificationStatus;

    @Column(name = "faculty_verification_reviewed_by")
    private String facultyVerificationReviewedBy;

    @Column(name = "faculty_verification_rejection_reason")
    private String facultyVerificationRejectionReason;

    // Subscription
    @Column(name = "subscription_plan")
    private String subscriptionPlan;

    @Column(name = "subscription_status")
    private String subscriptionStatus;

    @Column(name = "subscription_expiry")
    private LocalDateTime subscriptionExpiry;

    // Notification
    @Column(name = "fcm_token")
    private String fcmToken;

    // Analytics
    @Column(name = "ranking_score")
    private Integer rankingScore;

    @Column(name = "profile_completed")
    private Boolean profileCompleted;

    // Dates
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "faculty_verification_reviewed_at")
    private LocalDateTime facultyVerificationReviewedAt;
}
