CREATE KEYSPACE IF NOT EXISTS iotsensorcollector
    WITH
        replication = {
            'class': 'NetworkTopologyStrategy',
            'replication_factor': 3
        };
