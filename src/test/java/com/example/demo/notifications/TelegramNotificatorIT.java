package com.example.demo.notifications;

import com.example.demo.notifications.dto.Notification;
import com.example.demo.notifications.service.TelegramNotificationService;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@EnablePostgresIntegrationTest
public class TelegramNotificatorIT {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TelegramNotificationService telegramServiceMock;

    @Captor
    ArgumentCaptor<Notification> messageCaptor;

    @Test
    @DataSet(cleanAfter = true)
    @ExpectedDataSet(value = "mathExpression_created.json")
    void testSendRequestNotification() {
        //Act
        String actual = webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calculator/all")
                        .queryParam("number", "123")
                        .build())
                .exchange()

                //Assert
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        String expected = "{\"Avg\":2,\"Min\":1,\"Max\":3,\"Sum\":6}";

        assertEquals(expected, actual);
        verify(telegramServiceMock)
                .notify(messageCaptor.capture());

        Notification actualMessage = messageCaptor.getValue();
        Assertions.assertThat(actualMessage.getFunction()).isEqualTo("getCalculationResult");
    }
}
