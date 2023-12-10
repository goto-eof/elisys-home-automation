package com.andreidodu.elisyshomeautomation.resource.impl;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.request.DeviceRegistrationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import com.andreidodu.elisyshomeautomation.resource.DeviceResource;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeviceResourceImpl implements DeviceResource {

    final private DeviceService deviceService;

    @Override
    public ResponseEntity<ResponseStatusDTO> register(DeviceRegistrationDTO deviceRegistrationDTO) {
        return ResponseEntity.ok(deviceService.register(deviceRegistrationDTO));
    }

    @Override
    public ResponseEntity<DeviceDTO> getDevice(SensorRequestCommonDTO sensorRequestCommonDTO) {
        return ResponseEntity.ok(this.deviceService.retrieveDevice(sensorRequestCommonDTO));
    }
}
