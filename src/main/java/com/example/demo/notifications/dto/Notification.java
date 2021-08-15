package com.example.demo.notifications.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Notification {
    LocalDateTime callingTime;
    String function;
    String exception;
}
