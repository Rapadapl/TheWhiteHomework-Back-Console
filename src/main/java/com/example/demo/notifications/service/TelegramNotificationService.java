package com.example.demo.notifications.service;


import com.example.demo.notifications.dto.Notification;
import com.example.demo.notifications.dto.Summary;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramNotificationService implements NotificationService {

    private final WebClient webClient;
    @Value("${telegram.bot.token}")
    private  String botToken;
    @Value("${telegram.chat.id}")
    private  String chatId;

    @Autowired
    public TelegramNotificationService(WebClient webClient,
                                       @Autowired(required = false) String botToken,
                                       @Autowired(required = false) String chatId) {
        this.webClient = webClient;
        this.botToken = botToken;
        this.chatId = chatId;
    }


    @Override
    public void notify(Notification message) {
        StringBuilder notification = new StringBuilder()
                .append("Service was called at:")
                .append(message.getCallingTime())
                .append('\n')
                .append("Executed method:")
                .append(message.getFunction())
                .append('\n');

        if (message.getException().isEmpty()) {
            notification.append("Operation successful");

        } else {
            notification.append("Operation failed \n")
                        .append("Error clause ")
                        .append(message.getException());
        }
        sendMessageFromBot(notification.toString());
    }

    @Override
    public void summarize(Summary message) {
        StringBuilder summary = new StringBuilder()
                .append("Summary")
                .append("\n")
                .append("In a period of time from ")
                .append(message.getFromDateTime())
                .append(" to ")
                .append(message.getToDateTime())
                .append("was ")
                .append(message.getCount())
                .append("queries");
        sendMessageFromBot(summary.toString());
    }

    public void sendMessageFromBot(String botMessage) {
        UriComponents url = UriComponentsBuilder.newInstance()
                                                .scheme("https")
                                                .host("api.telegram.org")
                                                .path(String.format("/bot%s/sendMessage", botToken))
                                                .queryParam("chat_id", chatId)
                                                .queryParam("text", botMessage)
                                                .build();
        webClient.post()
                 .uri(url.toUri())
                 .retrieve()
                 .bodyToMono(String.class)
        //.doOnError(error -> log.error("An error has occurred {}", error.getMessage()))
                 .block()
        ;
        log.info("bot message send");
    }

}
