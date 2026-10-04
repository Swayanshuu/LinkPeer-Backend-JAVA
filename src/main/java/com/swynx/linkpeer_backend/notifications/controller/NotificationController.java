package com.swynx.linkpeer_backend.notifications.controller;

import com.google.firebase.auth.FirebaseToken;
import com.swynx.linkpeer_backend.notifications.dto.response.NotificationResponse;
import com.swynx.linkpeer_backend.notifications.dto.response.UnreadCountResponse;
import com.swynx.linkpeer_backend.notifications.mapper.NotificationMapper;
import com.swynx.linkpeer_backend.notifications.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import com.swynx.linkpeer_backend.notification.entity.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    public NotificationController(
            NotificationService notificationService,
            NotificationMapper notificationMapper
    ) {
        this.notificationService = notificationService;
        this.notificationMapper = notificationMapper;
    }

    // Get all notifications of the logged-in user
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getMyNotifications(
            HttpServletRequest request
    ) {
        FirebaseToken firebaseUser =
                (FirebaseToken) request.getAttribute("firebaseUser");

        String userId = firebaseUser.getUid();

        List<Notification> notifications =
                notificationService.getMyNotifications(userId);

        List<NotificationResponse> response = notifications.stream()
                .map(notificationMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    // Get unread notification count
    @GetMapping("/unread-count")
    public ResponseEntity<UnreadCountResponse> getUnreadCount(
            HttpServletRequest request
    ) {
        FirebaseToken firebaseUser =
                (FirebaseToken) request.getAttribute("firebaseUser");

        String userId = firebaseUser.getUid();

        long count = notificationService.getUnreadCount(userId);

        return ResponseEntity.ok(
                new UnreadCountResponse(count)
        );
    }

    // Mark one notification as read
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable UUID notificationId,
            HttpServletRequest request
    ) {
        FirebaseToken firebaseUser =
                (FirebaseToken) request.getAttribute("firebaseUser");

        String userId = firebaseUser.getUid();

        notificationService.markAsRead(userId, notificationId);

        return ResponseEntity.noContent().build();
    }

    // Mark all notifications as read
    @PutMapping("/read-all")
    public ResponseEntity<Void> markAllAsRead(
            HttpServletRequest request
    ) {
        FirebaseToken firebaseUser =
                (FirebaseToken) request.getAttribute("firebaseUser");

        String userId = firebaseUser.getUid();

        notificationService.markAllAsRead(userId);

        return ResponseEntity.noContent().build();
    }
}