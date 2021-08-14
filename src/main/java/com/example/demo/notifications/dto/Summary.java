package com.example.demo.notifications.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Summary {
    LocalDateTime fromDateTime;
    LocalDateTime toDateTime;
    int count;
}
