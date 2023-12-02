package com.andreidodu.elisyshomeautomation.resource.impl;

import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.RelayConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.MotionSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.RelayConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import com.andreidodu.elisyshomeautomation.model.RelayConfiguration;
import com.andreidodu.elisyshomeautomation.resource.MotionSensorResource;
import com.andreidodu.elisyshomeautomation.resource.RelayConfigurationResource;
import com.andreidodu.elisyshomeautomation.service.MotionSensorService;
import com.andreidodu.elisyshomeautomation.service.RelayConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RelayConfigurationResourceImpl implements RelayConfigurationResource {

    final private RelayConfigurationService relayConfigurationService;


    @Override
    public ResponseEntity<RelayConfigurationResponseDTO> getConfiguration(RelayConfigurationRequestDTO configurationRequestDTO) {
        return ResponseEntity.ok(this.relayConfigurationService.getConfiguration(configurationRequestDTO));
    }
}
