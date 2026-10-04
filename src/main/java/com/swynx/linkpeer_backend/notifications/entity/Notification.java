package com.swynx.linkpeer_backend.notification.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue
    private UUID id;

    // User who receives the notification
    @Column(name = "user_id", nullable = false)
    private String userId;

    // User who caused the notification
    @Column(name = "actor_user_id")
    private String actorUserId;

    // Related post, if applicable
    @Column(name = "post_id")
    private Long postId;

    // Related comment, if applicable
    @Column(name = "comment_id")
    private Long commentId;

    // Example: POST_LIKE, COMMENT, COMMENT_LIKE
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    // Whether the user has opened/read the notification
    @Column(name = "is_read")
    private boolean read;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}