package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dao.DeviceRepository;
import com.andreidodu.elisyshomeautomation.dao.RelayConfigurationRepository;
import com.andreidodu.elisyshomeautomation.dto.request.RelayConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.RelayConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.mapper.RelayConfigurationMapper;
import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.model.RelayConfiguration;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.RelayConfigurationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RelayConfigurationServiceImpl implements RelayConfigurationService {

    private final RelayConfigurationRepository relayConfigurationRepository;
    private final RelayConfigurationMapper relayConfigurationMapper;
    private final DeviceRepository deviceRepository;
    final private DeviceService deviceService;


    @Override
    public RelayConfigurationResponseDTO getConfiguration(RelayConfigurationRequestDTO configurationRequestDTO) {
        Optional<RelayConfiguration> configurationOptional = relayConfigurationRepository.findByDevice_MacAddress(configurationRequestDTO.getMacAddress());
        if (configurationOptional.isPresent()) {
            RelayConfigurationResponseDTO result = relayConfigurationMapper.toDTO(configurationOptional.get());
            log.info(result.toString());
            return result;
        }
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(configurationRequestDTO.getMacAddress());
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(configurationRequestDTO.getMacAddress(), configurationRequestDTO.getMacAddress());
            deviceOptional = this.deviceRepository.findByMacAddress(configurationRequestDTO.getMacAddress());
        }
        RelayConfigurationResponseDTO dto = loadDefaultConfiguration(configurationRequestDTO.getMacAddress());
        RelayConfiguration model = relayConfigurationMapper.toModel(dto);
        model.setDevice(deviceOptional.get());
        RelayConfigurationResponseDTO result = this.relayConfigurationMapper.toDTO(this.relayConfigurationRepository.save(model));
        log.info(result.toString());
        return result;
    }

    private RelayConfigurationResponseDTO loadDefaultConfiguration(String macAddress) {
        RelayConfigurationResponseDTO dto = new RelayConfigurationResponseDTO();
        dto.setMacAddress(macAddress);
        dto.setPowerOn(true);
        return dto;
    }


}
