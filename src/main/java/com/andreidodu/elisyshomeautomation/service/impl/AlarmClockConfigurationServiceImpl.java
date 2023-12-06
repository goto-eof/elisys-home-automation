package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dto.request.AlarmClockConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationCronResponseDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.mapper.AlarmClockConfigurationMapper;
import com.andreidodu.elisyshomeautomation.model.*;
import com.andreidodu.elisyshomeautomation.repository.*;
import com.andreidodu.elisyshomeautomation.service.AlarmClockConfigurationService;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AlarmClockConfigurationServiceImpl implements AlarmClockConfigurationService {

    public static final String DEVICE_NAME = "alarm clock";
    private final AlarmClockConfigurationRepository repository;
    private final AlarmClockConfigurationMapper mapper;
    private final DeviceRepository deviceRepository;
    private final DeviceService deviceService;

    @Value("${app.configuration.default.alarm-clock.alarm-interval-minutes}")
    private Integer defaultAlarmIntervalMinutes;

    @Value("${app.configuration.default.alarm-clock.timezone-seconds}")
    private Long defaultTimezoneSeconds;

    @Value("${app.configuration.default.alarm-clock.cron-list}")
    private List<String> defaultCronList;

    @Value("${app.configuration.default.alarm-clock.iamalive.interval}")
    private Long defaultIAmAliveIntervalSeconds;

    @Value("${app.configuration.default.alarm-clock.iamalive.endpoint}")
    private String defaultIAmAliveEndpoint;

    @Override
    public AlarmClockConfigurationResponseDTO getConfiguration(AlarmClockConfigurationRequestDTO configurationRequestDTO) {
        Optional<AlarmClockConfiguration> configurationOptional = repository.findByDevice_MacAddress(configurationRequestDTO.getMacAddress());
        if (configurationOptional.isPresent()) {
            AlarmClockConfigurationResponseDTO result = mapper.toDTO(configurationOptional.get());
            log.info(result.toString());
            return result;
        }
        AlarmClockConfigurationResponseDTO result = createNewConfiguration(configurationRequestDTO);
        log.info(result.toString());
        return result;
    }

    private AlarmClockConfigurationResponseDTO createNewConfiguration(AlarmClockConfigurationRequestDTO configurationRequestDTO) {
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(configurationRequestDTO.getMacAddress());
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(configurationRequestDTO.getMacAddress(), DEVICE_NAME, configurationRequestDTO.getMacAddress());
            deviceOptional = this.deviceRepository.findByMacAddress(configurationRequestDTO.getMacAddress());
        }
        AlarmClockConfigurationResponseDTO dto = loadDefaultConfiguration(configurationRequestDTO.getMacAddress());
        AlarmClockConfiguration model = mapper.toModel(dto);
        model.setDevice(deviceOptional.get());
        return this.mapper.toDTO(this.repository.save(model));
    }

    private AlarmClockConfigurationResponseDTO loadDefaultConfiguration(String macAddress) {
        AlarmClockConfigurationResponseDTO dto = new AlarmClockConfigurationResponseDTO();
        dto.setMacAddress(macAddress);
        dto.setAlarmIntervalMinutes(defaultAlarmIntervalMinutes);
        dto.setTimezoneSeconds(defaultTimezoneSeconds);
        dto.setCronList(defaultCronList.stream().map(cron -> new AlarmClockConfigurationCronResponseDTO(cron, "alarm")).toList());
        dto.setIAmAliveEndpoint(defaultIAmAliveEndpoint);
        dto.setIAmAliveIntervalSeconds(defaultIAmAliveIntervalSeconds);
        return dto;
    }
}
