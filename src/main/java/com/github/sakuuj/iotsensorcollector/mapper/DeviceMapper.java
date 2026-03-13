package com.github.sakuuj.iotsensorcollector.mapper;

import com.github.sakuuj.iotsensorcollector.dto.DeviceRegistrationRequest;
import com.github.sakuuj.iotsensorcollector.dto.DeviceResponse;
import com.github.sakuuj.iotsensorcollector.entity.DeviceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface DeviceMapper {

    DeviceEntity toEntity(String id, DeviceRegistrationRequest request);

    DeviceResponse toResponse(DeviceEntity entity);
}
