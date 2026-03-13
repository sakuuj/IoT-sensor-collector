package com.github.sakuuj.iotsensorcollector.dto;

import lombok.Builder;

@Builder
public record DeviceResponse(
        String id,
        String name,
        String measurementKind,
        String additionalInfo,
        Double minValue,
        Double maxValue
) {
}
