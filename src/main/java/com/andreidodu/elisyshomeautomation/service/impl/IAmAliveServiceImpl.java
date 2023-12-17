package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.entity.DeviceType;
import com.andreidodu.elisyshomeautomation.repository.DeviceRepository;
import com.andreidodu.elisyshomeautomation.entity.Alive;
import com.andreidodu.elisyshomeautomation.entity.Device;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.IAmAliveService;
import com.andreidodu.elisyshomeautomation.repository.IAmAliveRepository;
import com.andreidodu.elisyshomeautomation.dto.request.IAmAliveRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class IAmAliveServiceImpl implements IAmAliveService {

    private final static String DEVICE_NAME = "Unknown device";

    final private IAmAliveRepository iAmAliveRepository;
    final private DeviceRepository deviceRepository;
    final private DeviceService deviceService;

    @Override
    public ResponseStatusDTO update(final IAmAliveRequestDTO iAmAliveRequestDTO) {
        Optional<Alive> aliveOptional = iAmAliveRepository.findByDevice_MacAddress(iAmAliveRequestDTO.getMacAddress());
        ResponseStatusDTO status = new ResponseStatusDTO();
        if (aliveOptional.isPresent()) {
            Alive alive = aliveOptional.get();
            alive.setLastAckTimestamp(LocalDateTime.now());
            iAmAliveRepository.save(alive);
            status.setStatus(true);
            return status;
        }
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(iAmAliveRequestDTO.getMacAddress());
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(DeviceType.Unknown, iAmAliveRequestDTO.getMacAddress(), DEVICE_NAME, iAmAliveRequestDTO.getMacAddress());
            deviceOptional = this.deviceRepository.findByMacAddress(iAmAliveRequestDTO.getMacAddress());
        }
        Alive alive = createAliveModel(deviceOptional.get());
        iAmAliveRepository.save(alive);
        status.setStatus(true);
        return status;
    }

    private Alive createAliveModel(Device device) {
        Alive alive = new Alive();
        alive.setDevice(device);
        alive.setLastAckTimestamp(LocalDateTime.now());
        return alive;
    }

    @Override
    @Transactional
    public void updateByMacAddress(final String macAddress) {
        Optional<Alive> aliveOptional = iAmAliveRepository.findByDevice_MacAddress(macAddress);
        if (aliveOptional.isPresent()) {
            Alive alive = aliveOptional.get();
            alive.setLastAckTimestamp(LocalDateTime.now());
            iAmAliveRepository.flush();
            ;
            iAmAliveRepository.save(alive);
            iAmAliveRepository.flush();
            ;
            return;
        }
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(macAddress);
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(DeviceType.Unknown, macAddress, DEVICE_NAME, macAddress);
            deviceOptional = this.deviceRepository.findByMacAddress(macAddress);
        }
        Alive alive = createAliveModel(deviceOptional.get());
        iAmAliveRepository.save(alive);
    }

}
