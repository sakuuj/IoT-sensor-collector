package com.github.sakuuj.iotsensorcollector.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record DeviceRegistrationRequest(
        @NotEmpty String name,
        @NotEmpty String measurementKind,
        String additionalInfo,
        @NotNull Double minValue,
        @NotNull Double maxValue
) {
    @AssertTrue(message = "Max value should be greater than or equal to min value")
    public boolean maxValueToMinValue() {
        return maxValue > minValue;
    }
}
