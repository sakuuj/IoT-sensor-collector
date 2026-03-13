package com.github.sakuuj.iotsensorcollector.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("custom.flyway")
public record CustomFlywayProperties(
        List<String> schemas,
        List<String> sqlMigrationSuffixes,
        List<String> locations
) {
}
