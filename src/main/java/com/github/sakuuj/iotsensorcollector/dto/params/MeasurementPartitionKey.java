package com.github.sakuuj.iotsensorcollector.dto.params;

import lombok.Builder;

import java.time.Instant;

@Builder
public record MeasurementPartitionKey(
        String deviceId,
        Instant partitionTimestamp
) {
}
