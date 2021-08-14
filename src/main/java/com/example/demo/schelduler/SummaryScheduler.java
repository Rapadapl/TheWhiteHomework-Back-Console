package com.example.demo.schelduler;

import com.example.demo.notifications.dto.Summary;
import com.example.demo.notifications.service.NotificationService;
import com.example.demo.service.MathExpressionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@ConditionalOnProperty("scheduler.summary.cron")
@RequiredArgsConstructor
public class SummaryScheduler {

    private final MathExpressionService expressionService;
    private final List<NotificationService> notificationServiceList;

    @Scheduled(cron = "${scheduler.summary.cron}")
    void summarize() {

        LocalDateTime todayDateTime = LocalDateTime.now();
        LocalDateTime yesterdayDateTime = todayDateTime.minusDays(1);


        Summary message = Summary.builder()
                                 .fromDateTime(todayDateTime)
                                 .toDateTime(yesterdayDateTime)
                                 .count(expressionService.getBetweenDates(todayDateTime, yesterdayDateTime).size())
                                 .build();

        notificationServiceList.forEach(service -> service.summarize(message));
    }

}
