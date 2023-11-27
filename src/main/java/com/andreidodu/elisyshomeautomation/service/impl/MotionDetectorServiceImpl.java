package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.client.DiscordChannel;
import com.andreidodu.elisyshomeautomation.dao.DeviceRepository;
import com.andreidodu.elisyshomeautomation.dao.MotionDetectionRepository;
import com.andreidodu.elisyshomeautomation.dto.response.DiscordMessageDTO;
import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.model.MotionDetection;
import com.andreidodu.elisyshomeautomation.service.MotionDetectorService;
import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class MotionDetectorServiceImpl implements MotionDetectorService {

    final private DiscordChannel discordChannel;
    final private MotionDetectionRepository motionDetectionRepository;
    final private DeviceRepository deviceRepository;

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


}
