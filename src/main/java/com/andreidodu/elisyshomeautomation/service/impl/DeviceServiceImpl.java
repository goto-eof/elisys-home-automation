package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.request.DeviceRegistrationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import com.andreidodu.elisyshomeautomation.exception.ApplicationException;
import com.andreidodu.elisyshomeautomation.model.DeviceType;
import com.andreidodu.elisyshomeautomation.repository.DeviceRepository;
import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.mapper.DeviceMapper;
import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public DeviceDTO createNewDevice(final DeviceType type, final String macAddress, final String name, final String description) {
        Device model = new Device();
        model.setMacAddress(macAddress);
        model.setDescription(description);
        model.setName(name);
        model.setType(type);
        Device device = this.deviceRepository.save(model);
        return this.deviceMapper.toDTO(device);
    }

    @Override
    public ResponseStatusDTO register(DeviceRegistrationDTO deviceRegistrationDTO) {
        Optional<Device> deviceOptional = deviceRepository.findByMacAddress(deviceRegistrationDTO.getMacAddress());
        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();
            device.setType(deviceRegistrationDTO.getType());
            deviceRepository.save(device);
            ResponseStatusDTO response = new ResponseStatusDTO();
            response.setStatus(true);
            return response;
        }
        Device model = new Device();
        model.setMacAddress(deviceRegistrationDTO.getMacAddress());
        model.setDescription(deviceRegistrationDTO.getDescription());
        model.setName(deviceRegistrationDTO.getName());
        model.setType(deviceRegistrationDTO.getType());
        this.deviceRepository.save(model);
        ResponseStatusDTO response = new ResponseStatusDTO();
        response.setStatus(true);
        return response;
    }


    // TODO in the future filter also by owner (user)
    @Override
    public List<DeviceDTO> retrieveDevicesByType(DeviceType type) {
        List<Device> devices = deviceRepository.findByType(type);
        return deviceMapper.toDTO(devices);
    }

    @Override
    public DeviceDTO retrieveDevice(SensorRequestCommonDTO sensorRequestCommonDTO) {
        Optional<Device> deviceOptional = deviceRepository.findByMacAddress(sensorRequestCommonDTO.getMacAddress());
        if (deviceOptional.isEmpty()) {
            throw new ApplicationException("Entity not found");
        }
        Device device = deviceOptional.get();
        if (!DeviceType.WeatherStation.equals(device.getType())) {
            throw new ApplicationException("Invalid device type");
        }
        return deviceMapper.toDTO(device);
    }
}
