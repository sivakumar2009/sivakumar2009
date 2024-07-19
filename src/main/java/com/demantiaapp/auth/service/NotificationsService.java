package com.demantiaapp.auth.service;

import com.demantiaapp.auth.model.Notifications;

import java.sql.Timestamp;
import java.util.Date;

public interface NotificationsService {
    Notifications addNotifications(Notifications notifications);
    Notifications getNotifications(Timestamp timestamp);
}
