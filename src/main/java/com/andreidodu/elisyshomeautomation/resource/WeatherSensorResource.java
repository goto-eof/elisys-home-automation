package com.andreidodu.elisyshomeautomation.resource;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherByDateIntervalRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherByDateRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherSummaryDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/weather-sensor")
public interface WeatherSensorResource {

    @Operation(summary = "Supplies device configuration")
    @PostMapping(value = "/configuration")
    ResponseEntity<WeatherSensorConfigurationDTO> getConfiguration(@RequestBody SensorConfigurationRequestDTO configurationRequestDTO);

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

    @Operation(summary = "Calculate weather average by date interval")
    @PostMapping(value = "/averageByDateInterval")
    ResponseEntity<WeatherDTO> calculateAverageByDateInterval(@RequestBody WeatherByDateIntervalRequestDTO weatherByDateRequestDTO);

    @Operation(summary = "Retrieve minimum temperature by date")
    @PostMapping(value = "/minimumTemperatureByDate")
    ResponseEntity<WeatherDTO> getMinimumTemperatureByDate(@RequestBody WeatherByDateRequestDTO weatherByDateRequestDTO);

    @Operation(summary = "Retrieve maximum temperature by date")
    @PostMapping(value = "/maximumTemperatureByDate")
    ResponseEntity<WeatherDTO> getMaximumTemperatureByDate(@RequestBody WeatherByDateRequestDTO weatherByDateRequestDTO);

    @Operation(summary = "Retrieve today minimum temperature")
    @PostMapping(value = "/todayMinimumTemperature")
    ResponseEntity<WeatherDTO> getTodayMinimumTemperature(@RequestBody SensorRequestCommonDTO dto);

    @Operation(summary = "Retrieve today maximum temperature")
    @PostMapping(value = "/todayMaximumTemperature")
    ResponseEntity<WeatherDTO> getTodayMaximumTemperature(@RequestBody SensorRequestCommonDTO dto);

    @Operation(summary = "Retrieve today minimum humidity")
    @PostMapping(value = "/todayMinimumHumidity")
    ResponseEntity<WeatherDTO> getTodayMinimumHumidity(@RequestBody  SensorRequestCommonDTO dto);

    @Operation(summary = "Retrieve today maximum humidity")
    @PostMapping(value = "/todayMaximumHumidity")
    ResponseEntity<WeatherDTO> getTodayMaximumHumidity(@RequestBody  SensorRequestCommonDTO dto);

    @Operation(summary = "Retrieve minimum humidity by date")
    @PostMapping(value = "/minimumHumidityByDate")
    ResponseEntity<WeatherDTO> getMinimumHumidityByDate(@RequestBody WeatherByDateRequestDTO dto);

    @Operation(summary = "Retrieve maximum humidity by date")
    @PostMapping(value = "/maximumHumidityByDate")
    ResponseEntity<WeatherDTO> getMaximumHumidityByDate(@RequestBody WeatherByDateRequestDTO dto);

    @Operation(summary = "Weather summary by date (day)")
    @PostMapping(value = "/todaySummary")
    ResponseEntity<WeatherSummaryDTO> getTodaySummary(@RequestBody WeatherByDateRequestDTO dto);

    @Operation(summary = "Weather summary by date (night)")
    @PostMapping(value = "/lastNightSummary")
    ResponseEntity<WeatherSummaryDTO> getLastNightSummary(@RequestBody WeatherByDateRequestDTO dto);
}
