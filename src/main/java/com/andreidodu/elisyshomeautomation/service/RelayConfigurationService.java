package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.RelayConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.MotionSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.RelayConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;

public interface RelayConfigurationService {
    RelayConfigurationResponseDTO getConfiguration(RelayConfigurationRequestDTO configurationRequestDTO);

    RelayConfigurationResponseDTO switchOnOrOff(RelayConfigurationRequestDTO configurationRequestDTO, boolean powerOn);
}
