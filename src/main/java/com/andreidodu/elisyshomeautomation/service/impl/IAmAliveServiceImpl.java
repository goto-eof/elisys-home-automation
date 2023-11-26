package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dao.DeviceRepository;
import com.andreidodu.elisyshomeautomation.model.Alive;
import com.andreidodu.elisyshomeautomation.service.IAmAliveService;
import com.andreidodu.elisyshomeautomation.dao.IAmAliveRepository;
import com.andreidodu.elisyshomeautomation.dto.request.IAmAliveRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IAmAliveServiceImpl implements IAmAliveService {

    final private IAmAliveRepository iAmAliveRepository;
    final private DeviceRepository deviceRepository;

    public ResponseStatusDTO check(final IAmAliveRequestDTO iAmAliveRequestDTO) {
        Optional<Alive> aliveOptional = iAmAliveRepository.findByDevice_MacAddress(iAmAliveRequestDTO.getMacAddress());
        ResponseStatusDTO status = new ResponseStatusDTO();
        status.setStatus(false);
        if (aliveOptional.isPresent()) {
            final Alive alive = aliveOptional.get();
            alive.setLastAckTimestamp(new Date());
            iAmAliveRepository.save(alive);
            status.setStatus(true);
            return status;
        }
        Alive alive = new Alive();
        alive.setLastAckTimestamp(new Date());
        alive.setDevice(deviceRepository.findByMacAddress(iAmAliveRequestDTO.getMacAddress()).get());
        iAmAliveRepository.save(alive);
        status.setStatus(true);
        return status;
    }

}
