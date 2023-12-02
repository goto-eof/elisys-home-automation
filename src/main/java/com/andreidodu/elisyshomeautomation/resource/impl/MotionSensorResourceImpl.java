package com.andreidodu.elisyshomeautomation.resource.impl;

import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.MotionSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.resource.MotionSensorResource;
import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.service.MotionSensorService;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MotionSensorResourceImpl implements MotionSensorResource {

    final private MotionSensorService motionSensorService;

    @Override
    public ResponseEntity<MotionSensorConfigurationDTO> getConfiguration(SensorConfigurationRequestDTO configurationRequestDTO) {
        return ResponseEntity.ok(this.motionSensorService.getConfiguration(configurationRequestDTO));
    }

    @Override
    public ResponseEntity<ResponseStatusDTO> alert(@RequestBody AlertRequestDTO alertRequestDTO) {
        return ResponseEntity.ok(motionSensorService.alert(alertRequestDTO));
    }

}
