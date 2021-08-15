package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "MathExpressions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public
class MathExpressions {
    @Id
    @GeneratedValue
    UUID id;
    String number;
    String result;
    private LocalDateTime creationDate;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDateTime.now();
    }
}