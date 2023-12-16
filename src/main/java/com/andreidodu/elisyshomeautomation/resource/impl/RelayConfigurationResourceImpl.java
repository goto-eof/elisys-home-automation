package com.andreidodu.elisyshomeautomation.resource.impl;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.dto.request.RelayConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.RelayConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.entity.DeviceType;
import com.andreidodu.elisyshomeautomation.resource.RelayConfigurationResource;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.RelayConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
