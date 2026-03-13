package com.github.sakuuj.iotsensorcollector.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record MeasurementResponse(
        String deviceId,
        Instant partitionTimestamp,
        Instant timestamp,
        Integer sequenceNumber,
        Double value
) {
}
