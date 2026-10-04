package com.swynx.linkpeer_backend.notifications.repository;

import com.swynx.linkpeer_backend.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    // Get notifications received by a user
    List<Notification> findByUserIdOrderByCreatedAtDesc(String userId);

    // Get only unread notifications
    List<Notification> findByUserIdAndReadFalseOrderByCreatedAtDesc(
            String userId
    );

    // Count unread notifications
    long countByUserIdAndReadFalse(String userId);
}
