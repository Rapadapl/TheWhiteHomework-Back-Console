package com.example.demo.notifications.service;

import com.example.demo.entity.MathExpressions;
import com.example.demo.notifications.dto.Notification;
import com.example.demo.notifications.dto.Summary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class TelegramNotificationServiceTest {

    @Captor
    ArgumentCaptor<URI> uriCaptor;
    TelegramNotificationService telegramNotificationService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private WebClient webClient;

    @BeforeEach
    void setup() {
        spy(WebClient.builder().build());
        telegramNotificationService = new TelegramNotificationService(webClient, "666", "42");
    }

    @Test
    void sendRequestNotification() throws UnsupportedEncodingException {
        //Arrange
        LocalDateTime dateTime = LocalDateTime.parse("2021-09-06T19:59:21.619");
        Notification messageArg = Notification.builder()
                                              .callingTime(dateTime)
                                              .function("calledMethod")
                                              .exception("")
                                              .build();
        //Act
        telegramNotificationService.notify(messageArg);

        //Assert
        String expectedString = "https://api.telegram.org/bot666/sendMessage?chat_id=42&text=Service%20was%20called" +
                                "%20at:2021-09-06T19:59:21.619%0AExecuted%20method:calledMethod%0AOperation%20successful";
        verify(webClient.post()).uri(uriCaptor.capture());
        String actualString = uriCaptor.getValue().toString();
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    void sendSummary() {
        //Arrange
        LocalDateTime fromDateTime = LocalDateTime.parse("2021-09-06T19:59:21.619");
        LocalDateTime toDateTime = LocalDateTime.parse("2021-09-07T19:59:21.619");

        Summary summary = mock(Summary.class);

        when(summary.getFromDateTime()).thenReturn(fromDateTime);
        when(summary.getToDateTime()).thenReturn(toDateTime);

        List<MathExpressions> mathExpressions = new ArrayList<>();

        mathExpressions.add(MathExpressions.builder().number("111").build());
        mathExpressions.add(MathExpressions.builder().number("222").build());
        mathExpressions.add(MathExpressions.builder().number("333").build());

        when(summary.getCount()).thenReturn(mathExpressions.size());

        telegramNotificationService.summarize(summary);

        //Assert
        String expectedString = "https://api.telegram.org/bot666/sendMessage?chat_id=42&text=Summary" +
                                "%0AIn%20a%20period%20of%20time%20from%202021-09-06T19:59:21.619" +
                                "%20to%202021-09-07T19:59:21.619was%203queries";
        verify(webClient.post()).uri(uriCaptor.capture());
        String actualString = uriCaptor.getValue().toString();
        Assertions.assertEquals(expectedString, actualString);
    }
}