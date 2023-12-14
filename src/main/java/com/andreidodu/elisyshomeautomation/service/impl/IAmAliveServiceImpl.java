package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.model.DeviceType;
import com.andreidodu.elisyshomeautomation.repository.DeviceRepository;
import com.andreidodu.elisyshomeautomation.model.Alive;
import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.IAmAliveService;
import com.andreidodu.elisyshomeautomation.repository.IAmAliveRepository;
import com.andreidodu.elisyshomeautomation.dto.request.IAmAliveRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public ResponseStatusDTO update(final IAmAliveRequestDTO iAmAliveRequestDTO) {
        Optional<Alive> aliveOptional = iAmAliveRepository.findByDevice_MacAddress(iAmAliveRequestDTO.getMacAddress());
        ResponseStatusDTO status = new ResponseStatusDTO();
        if (aliveOptional.isPresent()) {
            Alive alive = aliveOptional.get();
            alive.setLastAckTimestamp(new Date());
            iAmAliveRepository.save(alive);
            status.setStatus(true);
            return status;
        }
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(iAmAliveRequestDTO.getMacAddress());
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(DeviceType.Uknown, iAmAliveRequestDTO.getMacAddress(), DEVICE_NAME, iAmAliveRequestDTO.getMacAddress());
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
        alive.setLastAckTimestamp(new Date());
        return alive;
    }

    @Override
    public void updateByMacAddress(final String macAddress) {
        IAmAliveRequestDTO iAmAliveRequestDTO = new IAmAliveRequestDTO();
        iAmAliveRequestDTO.setMacAddress(macAddress);
        Optional<Alive> aliveOptional = iAmAliveRepository.findByDevice_MacAddress(iAmAliveRequestDTO.getMacAddress());
        ResponseStatusDTO status = new ResponseStatusDTO();
        if (aliveOptional.isPresent()) {
            Alive alive = aliveOptional.get();
            alive.setLastAckTimestamp(new Date());
            iAmAliveRepository.save(alive);
            status.setStatus(true);
            return;
        }
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(iAmAliveRequestDTO.getMacAddress());
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(DeviceType.Uknown, iAmAliveRequestDTO.getMacAddress(), DEVICE_NAME, iAmAliveRequestDTO.getMacAddress());
            deviceOptional = this.deviceRepository.findByMacAddress(iAmAliveRequestDTO.getMacAddress());
        }
        Alive alive = createAliveModel(deviceOptional.get());
        iAmAliveRepository.save(alive);
        status.setStatus(true);
        //return status;
    }

}
