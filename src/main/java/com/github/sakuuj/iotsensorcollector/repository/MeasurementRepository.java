package com.github.sakuuj.iotsensorcollector.repository;

import com.github.sakuuj.iotsensorcollector.entity.MeasurementEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.Repository;

import java.time.Instant;

@org.springframework.stereotype.Repository
public interface MeasurementRepository extends Repository<MeasurementEntity, MeasurementEntity.MeasurementId> {

    MeasurementEntity insert(MeasurementEntity entity);

    Slice<MeasurementEntity> findAllByIdDeviceIdAndIdPartitionTimestamp(
            Pageable pageable,
            String deviceId,
            Instant partitionTimestamp
    );
}
