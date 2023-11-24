package com.andreidodu.elisyshomeautomation.controller.impl;

import com.andreidodu.elisyshomeautomation.controller.MotionSensorResource;
import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.service.MotionDetectorService;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MotionDetectorResourceImpl implements MotionSensorResource {

    final private MotionDetectorService motionDetectorService;

    public ResponseEntity<ResponseStatusDTO> alert(@RequestBody AlertRequestDTO alertRequestDTO) {
        return ResponseEntity.ok(motionDetectorService.alert(alertRequestDTO));
    }

}
