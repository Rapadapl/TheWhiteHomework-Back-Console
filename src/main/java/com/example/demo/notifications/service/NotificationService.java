package com.example.demo.notifications.service;

import com.example.demo.notifications.dto.Notification;
import com.example.demo.notifications.dto.Summary;
import org.springframework.scheduling.annotation.Async;

public interface NotificationService {

    @Async
    void notify(Notification message);

    @Async
    void summarize(Summary message);

}
