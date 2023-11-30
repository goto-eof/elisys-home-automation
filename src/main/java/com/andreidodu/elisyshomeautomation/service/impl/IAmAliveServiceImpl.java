package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dao.DeviceRepository;
import com.andreidodu.elisyshomeautomation.model.Alive;
import com.andreidodu.elisyshomeautomation.model.Device;
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

    final private IAmAliveRepository iAmAliveRepository;
    final private DeviceRepository deviceRepository;

    public ResponseStatusDTO check(final IAmAliveRequestDTO iAmAliveRequestDTO) {
        Optional<Alive> aliveOptional = iAmAliveRepository.findByDevice_MacAddress(iAmAliveRequestDTO.getMacAddress());
        ResponseStatusDTO status = new ResponseStatusDTO();
        if (aliveOptional.isPresent()) {
            createAndSaveAliveModel(Optional.empty());
            status.setStatus(true);
            return status;
        }
        Optional<Device> deviceOptional = deviceRepository.findByMacAddress(iAmAliveRequestDTO.getMacAddress());
        createAndSaveAliveModel(deviceOptional);
        status.setStatus(true);
        return status;
    }

    private void createAndSaveAliveModel(Optional<Device> deviceOptional) {
        final Alive alive = new Alive();
        alive.setLastAckTimestamp(new Date());
        deviceOptional.ifPresent(alive::setDevice);
        iAmAliveRepository.save(alive);
    }

}
