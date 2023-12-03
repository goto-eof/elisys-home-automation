package com.andreidodu.elisyshomeautomation.resource;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.request.RelayConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherByDateIntervalRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherByDateRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.RelayConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/relay")
public interface RelayConfigurationResource {

    @Operation(summary = "Supplies device configuration")
    @PostMapping(value = "/configuration")
    ResponseEntity<RelayConfigurationResponseDTO> getConfiguration(@RequestBody RelayConfigurationRequestDTO configurationRequestDTO);

    @Operation(summary = "Enable")
    @PostMapping(value = "/enable")
    ResponseEntity<RelayConfigurationResponseDTO> enable(@RequestBody RelayConfigurationRequestDTO configurationRequestDTO);

    @Operation(summary = "Disable")
    @PostMapping(value = "/disable")
    ResponseEntity<RelayConfigurationResponseDTO> disable(@RequestBody RelayConfigurationRequestDTO configurationRequestDTO);


}
