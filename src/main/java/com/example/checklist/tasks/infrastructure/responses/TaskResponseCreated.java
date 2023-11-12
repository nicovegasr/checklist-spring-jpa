package com.example.checklist.tasks.infrastructure.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class TaskResponseCreated extends TaskResponse{
    private Long id;
}
