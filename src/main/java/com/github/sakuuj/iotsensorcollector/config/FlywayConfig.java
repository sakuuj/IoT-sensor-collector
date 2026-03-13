package com.github.sakuuj.iotsensorcollector.config;

import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.github.sakuuj.iotsensorcollector.config.properties.CustomFlywayProperties;
import com.ing.data.cassandra.jdbc.CassandraDataSource;
import com.ing.data.cassandra.jdbc.utils.ContactPoint;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CustomFlywayProperties.class)
public class FlywayConfig {

    private static final long DATASOURCE_REQUEST_TIMEOUT = 10_000L;

    @Bean(initMethod = "migrate")
    public Flyway flyway(CassandraProperties cassandraProperties, CustomFlywayProperties flywayProperties) {

        List<ContactPoint> contactPoints = cassandraProperties.getContactPoints()
                .stream()
                .map(contactPointString -> {
                    String[] contactPointSplit = contactPointString.split(":");
                    return ContactPoint.of(contactPointSplit[0], Integer.parseInt(contactPointSplit[1]));
                })
                .toList();

        var cassandraDataSource = new CassandraDataSource(contactPoints, cassandraProperties.getKeyspaceName());
        cassandraDataSource.setRequestTimeout(DATASOURCE_REQUEST_TIMEOUT);
        cassandraDataSource.setConsistency(DefaultConsistencyLevel.QUORUM.name());
        cassandraDataSource.setLocalDataCenter(cassandraProperties.getLocalDatacenter());

        return Flyway.configure()
                .dataSource(cassandraDataSource)
                .schemas(flywayProperties.schemas().toArray(String[]::new))
                .sqlMigrationSuffixes(flywayProperties.sqlMigrationSuffixes().toArray(String[]::new))
                .locations(flywayProperties.locations().toArray(String[]::new))
                .load();
    }
}
