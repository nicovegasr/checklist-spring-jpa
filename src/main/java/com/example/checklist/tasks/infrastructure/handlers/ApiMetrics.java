package com.example.checklist.tasks.infrastructure.handlers;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

@Component
public class ApiMetrics {
    private final MeterRegistry meterRegistry;

    public ApiMetrics(MeterRegistry registry) {
        this.meterRegistry = registry;
    }

    public Timer getTimerForEndpoint(String endpoint) {
        return Timer.builder("api.request.duration")
                .description("Request time at endpoint")
                .tags("endpoint", endpoint)
                .register(meterRegistry);
    }
}