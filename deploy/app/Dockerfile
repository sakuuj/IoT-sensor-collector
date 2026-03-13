FROM gradle:jdk25-alpine AS builder
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts ./
COPY src/ ./src/
RUN gradle clean bootJar

FROM amazoncorretto:25-alpine AS extractor
WORKDIR /app
COPY --from=builder /app/build/libs/* ./app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM amazoncorretto:25-alpine-jdk
WORKDIR /app
COPY --from=extractor app/dependencies/ ./
COPY --from=extractor app/spring-boot-loader/ ./
COPY --from=extractor app/snapshot-dependencies/ ./
COPY --from=extractor app/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

RUN find / -name java