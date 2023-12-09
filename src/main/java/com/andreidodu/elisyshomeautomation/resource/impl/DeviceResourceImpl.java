package com.andreidodu.elisyshomeautomation.resource.impl;

import com.andreidodu.elisyshomeautomation.dto.request.DeviceRegistrationDTO;
import com.andreidodu.elisyshomeautomation.dto.request.IAmAliveRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import com.andreidodu.elisyshomeautomation.resource.DeviceResource;
import com.andreidodu.elisyshomeautomation.resource.IAmAliveResource;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.IAmAliveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeviceResourceImpl implements DeviceResource {

    final private DeviceService deviceService;

    @Override
    public ResponseEntity<ResponseStatusDTO> register(DeviceRegistrationDTO deviceRegistrationDTO) {
        return ResponseEntity.ok(deviceService.register(deviceRegistrationDTO));
    }
}
