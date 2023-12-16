package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.request.DeviceRegistrationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import com.andreidodu.elisyshomeautomation.exception.ApplicationException;
import com.andreidodu.elisyshomeautomation.entity.DeviceType;
import com.andreidodu.elisyshomeautomation.repository.DeviceRepository;
import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.mapper.DeviceMapper;
import com.andreidodu.elisyshomeautomation.entity.Device;
import com.andreidodu.elisyshomeautomation.repository.IAmAliveRepository;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final IAmAliveRepository iAmAliveRepository;

    @Override
    public DeviceDTO createNewDevice(final DeviceType type, final String macAddress, final String name, final String description) {
        Optional<Device> optional = deviceRepository.findByMacAddress(macAddress);
        if (optional.isEmpty()) {
            Device model = new Device();
            model.setMacAddress(macAddress);
            model.setDescription(description);
            model.setName(name);
            model.setType(type);
            Device device = this.deviceRepository.save(model);
            return this.deviceMapper.toDTO(device);
        }
        return this.deviceMapper.toDTO(optional.get());
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


    @Override
    public List<DeviceDTO> retrieveDevicesByType(DeviceType type) {
        List<Device> devices = deviceRepository.findByType(type);
        return deviceMapper.toDTO(devices);
    }

    @Override
    public DeviceDTO retrieveDevice(SensorRequestCommonDTO sensorRequestCommonDTO) {
        Device device = deviceRepository.findByMacAddress(sensorRequestCommonDTO.getMacAddress())
                .orElseThrow(() -> new ApplicationException("Entity not found"));

        validateSensorType(sensorRequestCommonDTO, device);

        DeviceDTO dto = deviceMapper.toDTO(device);

        this.iAmAliveRepository.findByDevice_MacAddress(sensorRequestCommonDTO.getMacAddress())
                .ifPresent(alive -> dto.setLastAck(alive.getLastModifiedDate()));

        return dto;
    }

    private static void validateSensorType(SensorRequestCommonDTO sensorRequestCommonDTO, Device device) {
        if (!sensorRequestCommonDTO.getType().equals(device.getType())) {
            throw new ApplicationException("Invalid device type");
        }
    }

    @Override
    public DeviceDTO update(Long id, DeviceDTO dto) {
        Device model = this.deviceRepository.findById(id).orElseThrow(() ->
                new ApplicationException("Device not found"));
        this.deviceMapper.update(model, dto);
        Device newModel = this.deviceRepository.save(model);
        return this.deviceMapper.toDTO(newModel);
    }
}
