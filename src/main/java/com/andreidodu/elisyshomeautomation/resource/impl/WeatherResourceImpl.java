package com.andreidodu.elisyshomeautomation.resource.impl;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherByDateRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherDTO;
import com.andreidodu.elisyshomeautomation.resource.WeatherResource;
import com.andreidodu.elisyshomeautomation.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeatherResourceImpl implements WeatherResource {

    final private WeatherService weatherService;

    @Override
    public ResponseEntity<WeatherDTO> submitNew(WeatherDTO weatherDTO) {
        return ResponseEntity.ok(this.weatherService.insert(weatherDTO));
    }

    @Override
    public ResponseEntity<WeatherDTO> getLast(SensorRequestCommonDTO sensorRequestCommonDTO) {
        return ResponseEntity.ok(this.weatherService.getLast(sensorRequestCommonDTO.getMacAddress()));
    }


    @Override
    public ResponseEntity<List<WeatherDTO>> getAllByDate(@RequestBody WeatherByDateRequestDTO weatherByDateRequestDTO) {
        return ResponseEntity.ok(this.weatherService.getAllByDate(weatherByDateRequestDTO.getMacAddress(), weatherByDateRequestDTO.getDate()));
    }

    @Override
    public ResponseEntity<WeatherDTO> calculateAverageByDate(@RequestBody WeatherByDateRequestDTO weatherByDateRequestDTO) {
        return ResponseEntity.ok(this.weatherService.calculateAverageByDate(weatherByDateRequestDTO.getMacAddress(), weatherByDateRequestDTO.getDate()));
    }


}
