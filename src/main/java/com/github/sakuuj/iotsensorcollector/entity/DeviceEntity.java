package com.github.sakuuj.iotsensorcollector.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(
        value = "devices",
        keyspace = Constants.KEYSPACE_NAME
)
public record DeviceEntity(
        @PrimaryKey
        @PrimaryKeyColumn(
                name = "id",
                type = PrimaryKeyType.PARTITIONED,
                ordinal = 0
        )
        String id,

        @Column("name")
        String name,

        @Column("measurement_kind")
        String measurementKind,

        @Column("additional_info")
        String additionalInfo,

        @Column("min_value")
        double minValue,

        @Column("max_value")
        double maxValue
) {
}
