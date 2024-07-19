package com.demantiaapp.auth.repository;

import com.demantiaapp.auth.model.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
    Notifications findById(int id);
    Notifications findByTimestampIsBetween(Timestamp timestamp1, Timestamp timestamp2);
}
