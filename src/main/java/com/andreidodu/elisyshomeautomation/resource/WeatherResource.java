package com.andreidodu.elisyshomeautomation.resource;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherByDateRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/weather")
public interface WeatherResource {

    @Operation(summary = "Submits a new weather values")
    @PostMapping(value = "/submit")
    ResponseEntity<WeatherDTO> submitNew(@RequestBody WeatherDTO weatherDTO);

    @Operation(summary = "Returns the last weather dto")
    @PostMapping(value = "/last")
    ResponseEntity<WeatherDTO> getLast(@RequestBody SensorRequestCommonDTO sensorRequestCommonDTO);

    @Operation(summary = "Returns all weather records by date")
    @PostMapping(value = "/byDate")
    ResponseEntity<List<WeatherDTO>> getAllByDate(@RequestBody WeatherByDateRequestDTO weatherByDateRequestDTO);

    @Operation(summary = "Calculate weather average by date")
    @PostMapping(value = "/average")
    ResponseEntity<WeatherDTO> calculateAverageByDate(@RequestBody WeatherByDateRequestDTO weatherByDateRequestDTO);
}
