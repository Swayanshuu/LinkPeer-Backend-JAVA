package com.swynx.linkpeer_backend.notifications.service;

import com.swynx.linkpeer_backend.notification.entity.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> getMyNotifications(String userId);

    long getUnreadCount(String userId);

    void markAsRead(String userId, java.util.UUID notificationId);

    void markAllAsRead(String userId);
}