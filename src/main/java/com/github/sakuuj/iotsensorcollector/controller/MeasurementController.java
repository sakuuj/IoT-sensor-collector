package com.github.sakuuj.iotsensorcollector.controller;

import com.github.sakuuj.iotsensorcollector.dto.MeasurementRequest;
import com.github.sakuuj.iotsensorcollector.dto.MeasurementResponse;
import com.github.sakuuj.iotsensorcollector.dto.PageWithoutCount;
import com.github.sakuuj.iotsensorcollector.dto.params.MeasurementPartitionKey;
import com.github.sakuuj.iotsensorcollector.service.MeasurementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/api/v1/measurements",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;

    @GetMapping
    public PageWithoutCount<MeasurementResponse> find(MeasurementPartitionKey measurementPartitionKey) {

        return measurementService.findAllByPartitionId(measurementPartitionKey);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MeasurementResponse saveMeasurement(@RequestBody @Valid MeasurementRequest request) {

        return measurementService.save(request);
    }
}
