package com.andreidodu.elisyshomeautomation.resource.impl;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.RelayConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.MotionSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.RelayConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import com.andreidodu.elisyshomeautomation.model.DeviceType;
import com.andreidodu.elisyshomeautomation.model.RelayConfiguration;
import com.andreidodu.elisyshomeautomation.resource.MotionSensorResource;
import com.andreidodu.elisyshomeautomation.resource.RelayConfigurationResource;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.MotionSensorService;
import com.andreidodu.elisyshomeautomation.service.RelayConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RelayConfigurationResourceImpl implements RelayConfigurationResource {

    final private RelayConfigurationService relayConfigurationService;
    final private DeviceService deviceService;

    @Override
    public ResponseEntity<RelayConfigurationResponseDTO> getConfiguration(RelayConfigurationRequestDTO configurationRequestDTO) {
        return ResponseEntity.ok(this.relayConfigurationService.getConfiguration(configurationRequestDTO));
    }

    @Override
    public ResponseEntity<RelayConfigurationResponseDTO> enable(RelayConfigurationRequestDTO configurationRequestDTO) {
        return ResponseEntity.ok(this.relayConfigurationService.switchOnOrOff(configurationRequestDTO, true));
    }

    @Override
    public ResponseEntity<RelayConfigurationResponseDTO> disable(RelayConfigurationRequestDTO configurationRequestDTO) {
        return ResponseEntity.ok(this.relayConfigurationService.switchOnOrOff(configurationRequestDTO, false));
    }

    @Override
    public ResponseEntity<List<DeviceDTO>> getDevices() {
        return ResponseEntity.ok(this.deviceService.retrieveDevicesByType(DeviceType.Relay));
    }

}
