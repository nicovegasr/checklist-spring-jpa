package com.example.checklist.tasks.infrastructure.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class TaskResponse {
    private String timestamp;
    private int statusCode;
    private String statusName;
    private String message;
}
