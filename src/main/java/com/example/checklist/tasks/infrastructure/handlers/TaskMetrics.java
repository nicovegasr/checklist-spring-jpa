package com.example.checklist.tasks.infrastructure.handlers;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class TaskMetrics {
    private final Counter taskCreatedCounter;

    public TaskMetrics(MeterRegistry registry) {
        taskCreatedCounter = Counter.builder("tasks.created")
                .description("Number of tasks created")
                .register(registry);
    }

    public void incrementTaskCreated() {
        taskCreatedCounter.increment();
    }

}