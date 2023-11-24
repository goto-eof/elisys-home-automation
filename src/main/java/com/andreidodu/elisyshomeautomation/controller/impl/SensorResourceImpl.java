package com.andreidodu.elisyshomeautomation.controller.impl;

import com.andreidodu.elisyshomeautomation.dto.response.SensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.service.SensorConfigurationService;
import com.andreidodu.elisyshomeautomation.controller.SensorResource;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SensorResourceImpl implements SensorResource {

    final private SensorConfigurationService sensorConfigurationService;
    @Override
    public ResponseEntity<SensorConfigurationDTO> getConfiguration(SensorConfigurationRequestDTO configurationRequestDTO) {
        return ResponseEntity.ok(this.sensorConfigurationService.getConfiguration(configurationRequestDTO));
    }

}
