package com.swynx.linkpeer_backend.notifications.mapper;

import com.swynx.linkpeer_backend.notification.entity.Notification;
import com.swynx.linkpeer_backend.notifications.dto.response.NotificationResponse;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationResponse toResponse(Notification notification) {

        NotificationResponse response = new NotificationResponse();

        response.setId(notification.getId());
        response.setActorUserId(notification.getActorUserId());
        response.setPostId(notification.getPostId());
        response.setCommentId(notification.getCommentId());
        response.setType(notification.getType());
        response.setTitle(notification.getTitle());
        response.setBody(notification.getBody());
        response.setRead(notification.isRead());
        response.setCreatedAt(notification.getCreatedAt());

        return response;
    }
}