package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dao.DeviceRepository;
import com.andreidodu.elisyshomeautomation.model.Alive;
import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.IAmAliveService;
import com.andreidodu.elisyshomeautomation.dao.IAmAliveRepository;
import com.andreidodu.elisyshomeautomation.dto.request.IAmAliveRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class IAmAliveServiceImpl implements IAmAliveService {

    private final static String DEVICE_NAME = "uknown device";

    final private IAmAliveRepository iAmAliveRepository;
    final private DeviceRepository deviceRepository;
    final private DeviceService deviceService;

    public ResponseStatusDTO check(final IAmAliveRequestDTO iAmAliveRequestDTO) {
        Optional<Alive> aliveOptional = iAmAliveRepository.findByDevice_MacAddress(iAmAliveRequestDTO.getMacAddress());
        ResponseStatusDTO status = new ResponseStatusDTO();
        if (aliveOptional.isPresent()) {
            updateAliveModel(aliveOptional.get());
            status.setStatus(true);
            return status;
        }
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(iAmAliveRequestDTO.getMacAddress());
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(iAmAliveRequestDTO.getMacAddress(), DEVICE_NAME, iAmAliveRequestDTO.getMacAddress());
            deviceOptional = this.deviceRepository.findByMacAddress(iAmAliveRequestDTO.getMacAddress());
        }
        createAliveModelAndSave(deviceOptional.get());
        status.setStatus(true);
        return status;
    }

    private void createAliveModelAndSave(Device device) {
        Alive alive = new Alive();
        alive.setDevice(device);
        alive.setLastAckTimestamp(new Date());
        iAmAliveRepository.save(alive);
    }

    private void updateAliveModel(Alive alive) {
        alive.setLastAckTimestamp(new Date());
        iAmAliveRepository.save(alive);
    }

}
