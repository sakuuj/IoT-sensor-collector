package com.github.sakuuj.iotsensorcollector.controller.error;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ApiError(
        int status,
        Instant timestamp,
        String errorMessage
) {
}
