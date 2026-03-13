package com.github.sakuuj.iotsensorcollector.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;

@Builder
public record MeasurementRequest(
        @NotEmpty String deviceId,
        @NotNull Instant timestamp,
        @NotNull Long sequenceNumber,
        @NotNull Double value
) {
}
