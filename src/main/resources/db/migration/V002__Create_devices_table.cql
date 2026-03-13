CREATE TABLE IF NOT EXISTS iotsensorcollector.devices (
    id               TEXT PRIMARY KEY,
    name             TEXT,
    measurement_kind TEXT,
    additional_info  TEXT,
    min_value        DOUBLE,
    max_value        DOUBLE
);
