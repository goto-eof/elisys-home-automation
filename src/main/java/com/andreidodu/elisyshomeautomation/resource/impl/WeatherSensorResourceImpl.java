package com.andreidodu.elisyshomeautomation.resource.impl;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherByDateIntervalRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherByDateRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.resource.WeatherSensorResource;
import com.andreidodu.elisyshomeautomation.service.WeatherSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeatherSensorResourceImpl implements WeatherSensorResource {

    final private WeatherSensorService weatherSensorService;

    @Override
    public ResponseEntity<WeatherSensorConfigurationDTO> getConfiguration(SensorConfigurationRequestDTO configurationRequestDTO) {
        return ResponseEntity.ok(this.weatherSensorService.getConfiguration(configurationRequestDTO));
    }

    @Override
    public ResponseEntity<WeatherDTO> submitNew(WeatherDTO weatherDTO) {
        return ResponseEntity.ok(this.weatherSensorService.insert(weatherDTO));
    }

    @Override
    public ResponseEntity<WeatherDTO> getLast(SensorRequestCommonDTO dto) {
        return ResponseEntity.ok(this.weatherSensorService.getLast(dto.getMacAddress()));
    }


    @Override
    public ResponseEntity<List<WeatherDTO>> getAllByDate(@RequestBody WeatherByDateRequestDTO dto) {
        return ResponseEntity.ok(this.weatherSensorService.getAllByDate(dto.getMacAddress(), dto.getDate()));
    }

    @Override
    public ResponseEntity<WeatherDTO> calculateAverageByDate(@RequestBody WeatherByDateRequestDTO dto) {
        return ResponseEntity.ok(this.weatherSensorService.calculateAverageByDate(dto.getMacAddress(), dto.getDate()));
    }

    @Override
    public ResponseEntity<WeatherDTO> calculateAverageByDateInterval(WeatherByDateIntervalRequestDTO dto) {
        return ResponseEntity.ok(this.weatherSensorService.calculateAverageByIntervalDate(dto.getMacAddress(), dto.getDateStart(), dto.getDateEnd()));
    }

}
