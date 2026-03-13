package com.github.sakuuj.iotsensorcollector.service;

import com.github.sakuuj.iotsensorcollector.dto.DeviceRegistrationRequest;
import com.github.sakuuj.iotsensorcollector.dto.DeviceResponse;
import com.github.sakuuj.iotsensorcollector.entity.DeviceEntity;
import com.github.sakuuj.iotsensorcollector.exception.NotFoundException;
import com.github.sakuuj.iotsensorcollector.generator.UuidGenerator;
import com.github.sakuuj.iotsensorcollector.mapper.DeviceMapper;
import com.github.sakuuj.iotsensorcollector.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final UuidGenerator uuidGenerator;

    private final DeviceMapper deviceMapper;
    private final DeviceRepository deviceRepository;

    public DeviceResponse registerDevice(DeviceRegistrationRequest registrationRequest) {
        DeviceEntity entity = deviceMapper.toEntity(uuidGenerator.generate().toString(), registrationRequest);
        DeviceEntity insertedEntity = deviceRepository.insert(entity);

        return deviceMapper.toResponse(insertedEntity);
    }

    public DeviceResponse findById(String id) {

        return deviceRepository.findById(id)
                .map(deviceMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Entity with id '%s' is not found".formatted(id)));
    }
}
