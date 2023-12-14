package com.andreidodu.elisyshomeautomation.resource.impl;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.dto.request.AlarmClockConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.model.DeviceType;
import com.andreidodu.elisyshomeautomation.resource.AlarmClockResource;
import com.andreidodu.elisyshomeautomation.service.AlarmClockConfigurationService;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlarmClockResourceImpl implements AlarmClockResource {

    final private AlarmClockConfigurationService alarmClockConfigurationService;
    final private DeviceService deviceService;

    @Override
    public ResponseEntity<AlarmClockConfigurationResponseDTO> getConfiguration(@RequestBody AlarmClockConfigurationRequestDTO configurationRequestDTO) {
        return ResponseEntity.ok(alarmClockConfigurationService.getConfiguration(configurationRequestDTO));
    }

    @Override
    public ResponseEntity<List<DeviceDTO>> getDevices() {
        return ResponseEntity.ok(this.deviceService.retrieveDevicesByType(DeviceType.AlarmClock));
    }

    @Override
    public ResponseEntity<AlarmClockConfigurationResponseDTO> update(Long id, AlarmClockConfigurationResponseDTO configurationRequestDTO) {
        return ResponseEntity.ok(alarmClockConfigurationService.update(id, configurationRequestDTO));
    }
}
