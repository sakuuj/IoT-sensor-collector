CREATE TABLE IF NOT EXISTS iotsensorcollector.measurements (
    device_id           TEXT,
    partition_timestamp TIMESTAMP,
    timestamp           TIMESTAMP,
    sequence_number     BIGINT,
    value               DOUBLE,

    PRIMARY KEY ((device_id, partition_timestamp), timestamp, sequence_number)
);
