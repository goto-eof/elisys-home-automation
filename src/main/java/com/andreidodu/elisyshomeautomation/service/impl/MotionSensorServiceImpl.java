package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.client.DiscordChannel;
import com.andreidodu.elisyshomeautomation.model.DeviceType;
import com.andreidodu.elisyshomeautomation.repository.DeviceRepository;
import com.andreidodu.elisyshomeautomation.repository.MotionDetectionRepository;
import com.andreidodu.elisyshomeautomation.repository.MotionSensorConfigurationRepository;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.DiscordMessageDTO;
import com.andreidodu.elisyshomeautomation.dto.response.MotionSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.mapper.SensorConfigurationMapper;
import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.model.MotionDetection;
import com.andreidodu.elisyshomeautomation.model.MotionSensorConfiguration;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.MotionSensorService;
import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MotionSensorServiceImpl implements MotionSensorService {

    public static final String DEVICE_NAME = "motion sensor";
    @Value("${app.configuration.default.motion.sensor.iamalive.interval.seconds}")
    private Long iAmAliveIntervalSeconds;

    @Value("${app.configuration.default.motion.sensor.iamalive.endpoint}")
    private String iAmAliveEndpoint;

    @Value("${app.configuration.default.motion.sensor.alert.endpoint}")
    private String alertEndpoint;

    @Value("${app.configuration.default.motion.sensor.crontab}")
    private String crontab;

    @Value("${app.configuration.default.motion.sensor.crontab.timezone}")
    private Integer timezoneOffsetSec;

    private final MotionSensorConfigurationRepository motionSensorConfigurationRepository;
    private final SensorConfigurationMapper sensorConfigurationMapper;
    private final DeviceRepository deviceRepository;
    final private DiscordChannel discordChannel;
    final private MotionDetectionRepository motionDetectionRepository;
    final private DeviceService deviceService;

    public ResponseStatusDTO alert(final AlertRequestDTO alertRequestDTO) {
        ResponseStatusDTO status = new ResponseStatusDTO();
        status.setStatus(true);
        log.info("alert request received from " + alertRequestDTO.getMacAddress());
        try {
            Device device = deviceRepository.findByMacAddress(alertRequestDTO.getMacAddress()).get();
            DiscordMessageDTO discordMessageDTO = new DiscordMessageDTO();
            discordMessageDTO.setContent((new Date()) + " - motion detected by [" + alertRequestDTO.getMacAddress() + "][" + device.getDescription() + "]");
            log.info("sending to discord channel...");
            discordChannel.sendMessage(discordMessageDTO);
            log.info("message sent successfully to Discord channel");
            createAndSaveMotionDetectionModel(device);
        } catch (Exception e) {
            status.setStatus(false);
            log.error("Unable to communicate with Discord: {}", e.getMessage());
        }
        return status;
    }

    private void createAndSaveMotionDetectionModel(Device device) {
        MotionDetection motionDetection = new MotionDetection();
        motionDetection.setDevice(device);
        motionDetection.setLastTimestamp(new Date());
        motionDetectionRepository.save(motionDetection);
    }


    @Override
    public MotionSensorConfigurationDTO getConfiguration(SensorConfigurationRequestDTO motionSensorConfigurationRequestDTO) {
        Optional<MotionSensorConfiguration> sensorConfigurationOptional = motionSensorConfigurationRepository.findByDevice_MacAddress(motionSensorConfigurationRequestDTO.getMacAddress());
        if (sensorConfigurationOptional.isPresent()) {
            MotionSensorConfigurationDTO result = sensorConfigurationMapper.toDTO(sensorConfigurationOptional.get());
            log.info(result.toString());
            return result;
        }
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(motionSensorConfigurationRequestDTO.getMacAddress());
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(DeviceType.MotionDetector, motionSensorConfigurationRequestDTO.getMacAddress(), DEVICE_NAME, motionSensorConfigurationRequestDTO.getMacAddress());
            deviceOptional = this.deviceRepository.findByMacAddress(motionSensorConfigurationRequestDTO.getMacAddress());
        }
        MotionSensorConfigurationDTO dto = loadDefaultConfiguration(motionSensorConfigurationRequestDTO.getMacAddress());
        MotionSensorConfiguration model = sensorConfigurationMapper.toModel(dto);
        model.setDevice(deviceOptional.get());
        MotionSensorConfigurationDTO result = this.sensorConfigurationMapper.toDTO(this.motionSensorConfigurationRepository.save(model));
        log.info(result.toString());
        return result;
    }

    private MotionSensorConfigurationDTO loadDefaultConfiguration(String macAddress) {
        MotionSensorConfigurationDTO dto = new MotionSensorConfigurationDTO();
        dto.setAlertEndpoint(alertEndpoint);
        dto.setMacAddress(macAddress);
        dto.setIAmAliveIntervalSeconds(iAmAliveIntervalSeconds);
        dto.setIAmAliveEndpoint(iAmAliveEndpoint);
        dto.setCrontab(crontab);
        dto.setTimezoneOffsetSec(timezoneOffsetSec);
        return dto;
    }


}
