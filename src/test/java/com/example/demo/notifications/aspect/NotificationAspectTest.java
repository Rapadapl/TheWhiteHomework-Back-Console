package com.example.demo.notifications.aspect;

import com.example.demo.notifications.annotation.Notificator;
import com.example.demo.notifications.dto.Notification;
import com.example.demo.notifications.service.NotificationService;
import com.example.demo.notifications.service.TelegramNotificationService;
import com.google.common.collect.Lists;
import org.aspectj.lang.ProceedingJoinPoint;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SoftAssertionsExtension.class})
class NotificationAspectTest {

    @Mock
    private TelegramNotificationService telegramNotificationService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ProceedingJoinPoint joinPoint;

    @Mock
    private Notificator notificator;

    @Captor
    ArgumentCaptor<Notification> messageCaptor;

    @Test
    void NotificationAboutRequest() throws Throwable {
        //Arrange
        NotificationAspect aspect = new NotificationAspect(Lists.newArrayList(telegramNotificationService));

        when(joinPoint.getSignature().getName()).thenReturn("getCalculationResult");

        //Act
        aspect.NotificationAboutRequest(joinPoint, notificator);

        //Assert
        verify(telegramNotificationService).notify(messageCaptor.capture());

        Notification actualMessage = messageCaptor.getValue();
        assertThat(actualMessage.getFunction()).isEqualTo("getCalculationResult");
    }

    @Test
    void NotificationWithException(SoftAssertions softly) throws Throwable {
        //Arrange
        NotificationAspect aspect = new NotificationAspect(Lists.newArrayList(telegramNotificationService));

        when(joinPoint.getSignature().getName()).thenReturn("getCalculationResult");
        when(joinPoint.proceed()).thenThrow(new IllegalArgumentException("Argument is null or has illegal type"));


        //Act & assert
        assertThrows(IllegalArgumentException.class, () -> aspect.NotificationAboutRequest(joinPoint, notificator));
        verify(telegramNotificationService)
                .notify(messageCaptor.capture());

        Notification actualMessage = messageCaptor.getValue();

        softly.assertThat(actualMessage.getFunction()).isEqualTo("getCalculationResult");
        softly.assertThat(actualMessage.getException()).isEqualTo("Argument is null or has illegal type");
}
}