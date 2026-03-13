package com.github.sakuuj.iotsensorcollector.generator;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class InstantGeneratorImpl implements InstantGenerator {

    @Override
    public Instant generate() {

        return Instant.now();
    }
}
