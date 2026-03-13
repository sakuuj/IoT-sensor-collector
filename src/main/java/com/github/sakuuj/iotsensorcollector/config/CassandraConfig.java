package com.github.sakuuj.iotsensorcollector.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration(proxyBeanMethods = false)
@EnableCassandraRepositories(basePackages = "com.github.sakuuj.iotsensorcollector.repository")
public class CassandraConfig {
}
