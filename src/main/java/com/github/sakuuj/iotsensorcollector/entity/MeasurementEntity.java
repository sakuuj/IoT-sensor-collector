package com.github.sakuuj.iotsensorcollector.entity;

import lombok.Builder;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;

@Table(
        value = "measurements",
        keyspace = Constants.KEYSPACE_NAME
)
public record MeasurementEntity(
        @PrimaryKey
        MeasurementId id,

        @Column("value")
        double value
) {
    @Builder
    @PrimaryKeyClass
    public record MeasurementId(
            @PrimaryKeyColumn(
                    name = "device_id",
                    type = PrimaryKeyType.PARTITIONED,
                    ordinal = 0
            )
            String deviceId,

            @PrimaryKeyColumn(
                    name = "partition_timestamp",
                    type = PrimaryKeyType.PARTITIONED,
                    ordinal = 1
            )
            Instant partitionTimestamp,

            @PrimaryKeyColumn(
                    name = "timestamp",
                    type = PrimaryKeyType.CLUSTERED,
                    ordinal = 2,
                    ordering = Ordering.DESCENDING
            )
            Instant timestamp,

            @PrimaryKeyColumn(
                    name = "sequence_number",
                    type = PrimaryKeyType.CLUSTERED,
                    ordinal = 3,
                    ordering = Ordering.DESCENDING
            )
            long sequenceNumber
    ) {
    }
}
