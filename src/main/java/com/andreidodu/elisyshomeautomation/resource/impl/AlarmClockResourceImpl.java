package com.andreidodu.elisyshomeautomation.resource.impl;

import com.andreidodu.elisyshomeautomation.dto.request.AlarmClockConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.resource.AlarmClockResource;
import com.andreidodu.elisyshomeautomation.service.AlarmClockConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlarmClockResourceImpl implements AlarmClockResource {

    final private AlarmClockConfigurationService alarmClockConfigurationService;

    @Override
    public ResponseEntity<AlarmClockConfigurationResponseDTO> getConfiguration(@RequestBody AlarmClockConfigurationRequestDTO configurationRequestDTO) {
        return ResponseEntity.ok(alarmClockConfigurationService.getConfiguration(configurationRequestDTO));
    }
}
