package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dao.DeviceRepository;
import com.andreidodu.elisyshomeautomation.dao.SensorConfigurationRepository;
import com.andreidodu.elisyshomeautomation.dto.response.SensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.mapper.SensorConfigurationMapper;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.model.SensorConfiguration;
import com.andreidodu.elisyshomeautomation.service.SensorConfigurationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SensorConfigurationServiceImpl implements SensorConfigurationService {


    @Value("${app.configuration.default.motion.sensor.iamalive.interval.seconds}")
    private Long iAmAliveIntervalSeconds;

    @Value("${app.configuration.default.motion.sensor.iamalive.endpoint}")
    private String iAmAliveEndpoint;

    @Value("${app.configuration.default.motion.sensor.alert.endpoint}")
    private String alertEndpoint;

    @Value("${app.configuration.default.motion.sensor.crontab}")
    private String crontab;

    private final SensorConfigurationRepository sensorConfigurationRepository;
    private final SensorConfigurationMapper sensorConfigurationMapper;
    private final DeviceRepository deviceRepository;

    @Override
    public SensorConfigurationDTO getConfiguration(SensorConfigurationRequestDTO motionSensorConfigurationRequestDTO) {
        Optional<SensorConfiguration> sensorConfigurationOptional = sensorConfigurationRepository.findByDevice_MacAddress(motionSensorConfigurationRequestDTO.getMacAddress());
        if (sensorConfigurationOptional.isPresent()) {
            SensorConfigurationDTO result = sensorConfigurationMapper.toDTO(sensorConfigurationOptional.get());
            log.info(result.toString());
            return result;
        }
        Device device = new Device();
        device.setMacAddress(motionSensorConfigurationRequestDTO.getMacAddress());
        device.setDescription(motionSensorConfigurationRequestDTO.getMacAddress());
        device = deviceRepository.save(device);

        SensorConfigurationDTO dto = loadDefaultConfiguration(motionSensorConfigurationRequestDTO.getMacAddress());
        SensorConfiguration model = sensorConfigurationMapper.toModel(dto);
        model.setDevice(device);
        SensorConfigurationDTO result = this.sensorConfigurationMapper.toDTO(this.sensorConfigurationRepository.save(model));
        log.info(result.toString());
        return result;
    }

    private SensorConfigurationDTO loadDefaultConfiguration(String macAddress) {
        System.out.println("ALERT ENDPOINT: " + alertEndpoint);
        SensorConfigurationDTO dto = new SensorConfigurationDTO();
        dto.setAlertEndpoint(alertEndpoint);
        dto.setMacAddress(macAddress);
        dto.setIAmAliveIntervalSeconds(iAmAliveIntervalSeconds);
        dto.setIAmAliveEndpoint(iAmAliveEndpoint);
        dto.setCrontab(crontab);
        return dto;
    }

}
