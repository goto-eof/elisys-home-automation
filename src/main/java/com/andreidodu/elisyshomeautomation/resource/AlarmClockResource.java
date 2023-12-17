package com.andreidodu.elisyshomeautomation.resource;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.dto.request.AlarmClockConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.dto.response.MotionSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/alarm-clock")
public interface AlarmClockResource {

    @Operation(summary = "Supplies device configuration and updates alive entity")
    @PostMapping(value = "/configuration")
    ResponseEntity<AlarmClockConfigurationResponseDTO> getConfiguration(@RequestBody AlarmClockConfigurationRequestDTO configurationRequestDTO);

    @Operation(summary = "Supplies device configuration and does not updates alive entity")
    @PostMapping(value = "/configurationWithNoAliveUpdate")
    ResponseEntity<AlarmClockConfigurationResponseDTO> getConfigurationNoUpdateIAmAlive(@RequestBody AlarmClockConfigurationRequestDTO configurationRequestDTO);

    @Operation(summary = "Returns alarm clock devices")
    @GetMapping(value = "/devices")
    ResponseEntity<List<DeviceDTO>> getDevices();

    @Operation(summary = "Updates Alarm Clock Configuration")
    @PutMapping(value = "/id/{id}")
    ResponseEntity<AlarmClockConfigurationResponseDTO> update(@PathVariable Long id, @RequestBody AlarmClockConfigurationResponseDTO configurationRequestDTO);

}
