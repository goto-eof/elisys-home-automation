package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dto.request.AlarmClockConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationCronResponseDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.exception.ApplicationException;
import com.andreidodu.elisyshomeautomation.mapper.AlarmClockConfigurationMapper;
import com.andreidodu.elisyshomeautomation.model.*;
import com.andreidodu.elisyshomeautomation.repository.*;
import com.andreidodu.elisyshomeautomation.service.AlarmClockConfigurationService;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.IAmAliveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final IAmAliveService iAmAliveService;
    private final AlarmClockConfigurationCronRepository alarmClockConfigurationCronRepository;

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
            AlarmClockConfiguration configuration = configurationOptional.get();
            AlarmClockConfigurationResponseDTO result = mapper.toDTO(configuration);
            log.info(result.toString());
            iAmAliveService.updateByMacAddress(configurationRequestDTO.getMacAddress());
            return result;
        }
        AlarmClockConfigurationResponseDTO result = createNewConfiguration(configurationRequestDTO);
        log.info(result.toString());
        iAmAliveService.updateByMacAddress(configurationRequestDTO.getMacAddress());
        return result;
    }

    @Override
    public AlarmClockConfigurationResponseDTO update(Long id, AlarmClockConfigurationResponseDTO dto) {
        validateDTOForUpdate(id, dto);
        Optional<AlarmClockConfiguration> modelOptional = this.repository.findByDevice_MacAddress(dto.getMacAddress());
        validateModelForUpdate(modelOptional);
        AlarmClockConfiguration model = modelOptional.get();
        this.mapper.update(model, dto);
        List<AlarmClockConfigurationCron> cronList = model.getCronList();
        cronList.forEach(item -> {
            Optional<AlarmClockConfigurationCronResponseDTO> dtoCron = dto.getCronList().stream().filter(i -> item.getId().equals(i.getId())).findFirst();
            dtoCron.ifPresent(alarmClockConfigurationCronResponseDTO -> mapper.update1(item, alarmClockConfigurationCronResponseDTO));
        });
        List<Long> ids = dto.getCronList().stream().map(AlarmClockConfigurationCronResponseDTO::getId).toList();

        cronList.removeIf(item -> !ids.contains(item.getId()));

        for (AlarmClockConfigurationCronResponseDTO alarmClockConfigurationCronResponseDTO : dto.getCronList()) {
            if (alarmClockConfigurationCronResponseDTO.getId() == null) {
                AlarmClockConfigurationCron cronModel = this.mapper.toModel(alarmClockConfigurationCronResponseDTO);
                cronModel.setConfiguration(model);
                this.alarmClockConfigurationCronRepository.save(cronModel);
            }
        }

        AlarmClockConfiguration newModel = this.repository.save(model);
        return this.mapper.toDTO(newModel);
    }

    private static void validateModelForUpdate(Optional<AlarmClockConfiguration> modelOptional) {
        if (modelOptional.isEmpty()) {
            throw new ApplicationException("Entity not found");
        }
    }

    private static void validateDTOForUpdate(Long id, AlarmClockConfigurationResponseDTO dto) {
        if (id == null) {
            throw new ApplicationException("invalid id");
        }
        if (!id.equals(dto.getId())) {
            throw new ApplicationException("id not matching");
        }
        for (AlarmClockConfigurationCronResponseDTO cron : dto.getCronList()) {
            if (cron.getCron() == null || cron.getCron().trim().isEmpty()) {
                throw new ApplicationException("invalid cron value");
            }
        }
    }

    public AlarmClockConfigurationResponseDTO createNewConfiguration(AlarmClockConfigurationRequestDTO configurationRequestDTO) {
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(configurationRequestDTO.getMacAddress());
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(DeviceType.AlarmClock, configurationRequestDTO.getMacAddress(), DEVICE_NAME, configurationRequestDTO.getMacAddress());
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
