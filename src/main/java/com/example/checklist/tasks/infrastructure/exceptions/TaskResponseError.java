package com.example.checklist.tasks.infrastructure.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class TaskResponseError{
    private String timestamp;
    private int statusCode;
    private String statusName;
    private String errorMessage;
}