package com.example.demo.notifications.aspect;


import com.example.demo.notifications.annotation.Notificator;
import com.example.demo.notifications.dto.Notification;
import com.example.demo.notifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Aspect
@Service
@RequiredArgsConstructor
public class NotificationAspect {


    private final List<NotificationService> notificationServices;

    @Around(value = "@annotation(notificatorBot)")
    public Object NotificationAboutRequest(ProceedingJoinPoint joinPoint, Notificator notificatorBot) throws Throwable {

        LocalDateTime callTime = LocalDateTime.now();

        String methodName = joinPoint.getSignature().getName();
        String exception = "";
        Object proceed;
        try {
            proceed = joinPoint.proceed();
        } catch (Exception e) {
            exception = e.getMessage();
            throw e;
        } finally {
            Notification message = Notification.builder()
                                               .callingTime(callTime)
                                               .function(methodName)
                                               .exception(exception)
                                               .build();
            notificationServices.forEach(service -> service.notify(message));
        }
        return proceed;
    }
}