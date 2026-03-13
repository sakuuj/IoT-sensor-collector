package com.github.sakuuj.iotsensorcollector.controller;

import com.github.sakuuj.iotsensorcollector.dto.DeviceRegistrationRequest;
import com.github.sakuuj.iotsensorcollector.dto.DeviceResponse;
import com.github.sakuuj.iotsensorcollector.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/api/v1/devices",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/{id}")
    public DeviceResponse findById(@PathVariable String id) {

        return deviceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceResponse registerDevice(@RequestBody @Valid DeviceRegistrationRequest registrationRequest) {

        return deviceService.registerDevice(registrationRequest);
    }
}
