package com.github.sakuuj.iotsensorcollector.repository;

import com.github.sakuuj.iotsensorcollector.entity.DeviceEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface DeviceRepository extends Repository<DeviceEntity, String> {

    Optional<DeviceEntity> findById(String id);

    DeviceEntity insert(DeviceEntity entity);
}
