package com.andreidodu.elisyshomeautomation.resource;

import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.MotionSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/motion-sensor")
public interface MotionSensorResource {

    @Operation(summary = "Supplies device configuration")
    @PostMapping(value = "/configuration")
    ResponseEntity<MotionSensorConfigurationDTO> getConfiguration(@RequestBody SensorConfigurationRequestDTO configurationRequestDTO);

    @Operation(summary = "Alert the server that the device detected a motion")
    @PostMapping(value = "/alert")
    ResponseEntity<ResponseStatusDTO> alert(@RequestBody AlertRequestDTO alertRequestDTO);
}
