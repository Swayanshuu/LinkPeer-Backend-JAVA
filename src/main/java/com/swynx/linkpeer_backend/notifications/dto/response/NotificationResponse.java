package com.swynx.linkpeer_backend.notifications.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class NotificationResponse {

    private UUID id;

    private String actorUserId;

    private Long postId;

    private Long commentId;

    private String type;

    private String title;

    private String body;

    private boolean read;

    private LocalDateTime createdAt;
}