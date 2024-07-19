package com.demantiaapp.auth.service;

import com.demantiaapp.auth.model.Notifications;
import com.demantiaapp.auth.repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationsServiceImpl implements NotificationsService {

    @Autowired
    NotificationsRepository notificationsRepository;

    @Override
    public Notifications addNotifications(Notifications notifications) {
        notificationsRepository.save(notifications);
        return notifications;
    }

}
