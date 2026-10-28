package com.swynx.linkpeer_backend.notifications.service;

import com.swynx.linkpeer_backend.common.exception.ResourceNotFoundException;
import com.swynx.linkpeer_backend.notifications.repository.NotificationRepository;
import com.swynx.linkpeer_backend.notifications.service.NotificationService;
import org.springframework.stereotype.Service;
import com.swynx.linkpeer_backend.notification.entity.Notification;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getMyNotifications(String userId) {
        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public long getUnreadCount(String userId) {
        return notificationRepository
                .countByUserIdAndReadFalse(userId);
    }

    @Override
    public void markAsRead(String userId, UUID notificationId) {

        Notification notification = notificationRepository
                .findById(notificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found"));

        // Make sure the notification belongs to this user
        if (!notification.getUserId().equals(userId)) {
            throw new ResourceNotFoundException("Notification not found");
        }

        notification.setRead(true);

        notificationRepository.save(notification);
    }

    @Override
    public void markAllAsRead(String userId) {

        List<Notification> notifications =
                notificationRepository
                        .findByUserIdAndReadFalseOrderByCreatedAtDesc(userId);

        for (Notification notification : notifications) {
            notification.setRead(true);
        }

        notificationRepository.saveAll(notifications);
    }

    @Override
    public Notification createNotification(String userId, String actorUserId, Long postId, Long commentId, String type, String title, String body) {
        Notification notification = new Notification();

        notification.setUserId(userId);
        notification.setActorUserId(actorUserId);
        notification.setPostId(postId);
        notification.setCommentId(commentId);
        notification.setTitle(title);
        notification.setBody(body);
        notification.setType(type);
        notification.setRead(false);
        return notificationRepository.save(notification);
    }
}