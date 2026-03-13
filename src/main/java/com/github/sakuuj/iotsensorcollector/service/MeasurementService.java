package com.github.sakuuj.iotsensorcollector.service;

import com.github.sakuuj.iotsensorcollector.dto.MeasurementRequest;
import com.github.sakuuj.iotsensorcollector.dto.MeasurementResponse;
import com.github.sakuuj.iotsensorcollector.dto.PageWithoutCount;
import com.github.sakuuj.iotsensorcollector.dto.params.MeasurementPartitionKey;
import com.github.sakuuj.iotsensorcollector.entity.MeasurementEntity;
import com.github.sakuuj.iotsensorcollector.mapper.MeasurementMapper;
import com.github.sakuuj.iotsensorcollector.mapper.PageMapper;
import com.github.sakuuj.iotsensorcollector.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private static final int FIND_ALL_MEASUREMENTS_PAGE_SIZE = 1000;

    private final PageMapper pageMapper;

    private final MeasurementMapper measurementMapper;
    private final MeasurementRepository measurementRepository;

    public PageWithoutCount<MeasurementResponse> findAllByPartitionId(MeasurementPartitionKey partitionKeyDto) {
        Slice<MeasurementEntity> foundEntities = measurementRepository.findAllByIdDeviceIdAndIdPartitionTimestamp(
                PageRequest.of(0, FIND_ALL_MEASUREMENTS_PAGE_SIZE),
                partitionKeyDto.deviceId(),
                partitionKeyDto.partitionTimestamp()
        );

        return pageMapper.toPageWithoutCount(foundEntities)
                .map(measurementMapper::toResponse);
    }

    public MeasurementResponse save(MeasurementRequest request) {
        MeasurementEntity entity = measurementMapper.toEntity(request, instant -> instant.truncatedTo(ChronoUnit.DAYS));
        entity = measurementRepository.insert(entity);

        return measurementMapper.toResponse(entity);
    }
}
