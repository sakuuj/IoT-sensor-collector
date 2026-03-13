package com.github.sakuuj.iotsensorcollector.mapper;

import com.github.sakuuj.iotsensorcollector.dto.MeasurementRequest;
import com.github.sakuuj.iotsensorcollector.dto.MeasurementResponse;
import com.github.sakuuj.iotsensorcollector.entity.MeasurementEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;
import java.util.function.Function;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface MeasurementMapper {

    @Mapping(target = "id", source = "request")
    MeasurementEntity toEntity(MeasurementRequest request, @Context Function<Instant, Instant> timestampTruncator);

    @Mapping(target = "deviceId", source = "deviceId")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "sequenceNumber", source = "sequenceNumber")
    @Mapping(target = "partitionTimestamp", source = "timestamp", qualifiedByName = "truncateTimestamp")
    MeasurementEntity.MeasurementId toEntityId(
            MeasurementRequest request,
            @Context Function<Instant, Instant> timestampTruncator
    );

    @Named("truncateTimestamp")
    default Instant truncateTimestamp(Instant sourceTimestamp, @Context Function<Instant, Instant> timestampTruncator) {
        return timestampTruncator.apply(sourceTimestamp);
    }

    @Mapping(target = "deviceId", source = "id.deviceId")
    @Mapping(target = "timestamp", source = "id.timestamp")
    @Mapping(target = "sequenceNumber", source = "id.sequenceNumber")
    MeasurementResponse toResponse(MeasurementEntity entity);
}
