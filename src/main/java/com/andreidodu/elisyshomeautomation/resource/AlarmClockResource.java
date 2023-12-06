package com.andreidodu.elisyshomeautomation.resource;

import com.andreidodu.elisyshomeautomation.dto.request.AlarmClockConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.dto.response.MotionSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/alarm-clock")
public interface AlarmClockResource {

    @Operation(summary = "Supplies device configuration")
    @PostMapping(value = "/configuration")
    ResponseEntity<AlarmClockConfigurationResponseDTO> getConfiguration(@RequestBody AlarmClockConfigurationRequestDTO configurationRequestDTO);

}
