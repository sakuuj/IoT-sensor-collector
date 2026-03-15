package com.github.sakuuj.iotsensorcollector.config;

import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.observability.ObservableCqlSessionFactoryBean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration(proxyBeanMethods = false)
@EnableCassandraRepositories(basePackages = "com.github.sakuuj.iotsensorcollector.repository")
public class CassandraConfig {

    @Bean
    public ObservableCqlSessionFactoryBean observableCqlSession(
            CqlSessionBuilder builder,
            ObservationRegistry registry
    ) {
        return new ObservableCqlSessionFactoryBean(builder, registry);
    }
}
