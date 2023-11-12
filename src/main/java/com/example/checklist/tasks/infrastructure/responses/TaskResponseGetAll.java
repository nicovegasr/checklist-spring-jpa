package com.example.checklist.tasks.infrastructure.responses;

import com.example.checklist.tasks.domain.models.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class TaskResponseGetAll extends TaskResponse{
    private Page<Task> tasks;
}


