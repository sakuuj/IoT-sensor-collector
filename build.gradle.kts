import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    java
    id("org.springframework.boot") version "3.5.11"
}

group = "com.github.sakuuj"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java.toolchain {
    languageVersion = JavaLanguageVersion.of(25)
}

springBoot {
    mainClass = "com.github.sakuuj.iotsensorcollector.IotSensorCollectorApplication"
}

repositories {
    mavenCentral()
}

val springBootVersion = "3.5.11"
val mapstructVersion = "1.6.3"
val flywayCassandraVersion = "12.1.0"
val cassandraJdbcWrapperVersion = "4.16.2"

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    implementation("org.mapstruct:mapstruct:${mapstructVersion}")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    implementation("io.opentelemetry:opentelemetry-exporter-otlp")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-cassandra")
    implementation("org.flywaydb:flyway-core")
    implementation("org.springframework:spring-jdbc")
    implementation("org.flywaydb:flyway-database-cassandra:$flywayCassandraVersion")
    implementation("com.ing.data:cassandra-jdbc-wrapper:$cassandraJdbcWrapperVersion")

    compileOnly(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    compileOnly("org.projectlombok:lombok")

    annotationProcessor(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

    testImplementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testRuntimeOnly(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<BootRun> {
    if (project.hasProperty("debug.jvm")) {
        val args = mutableListOf("-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005")
        jvmArgs = args
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}