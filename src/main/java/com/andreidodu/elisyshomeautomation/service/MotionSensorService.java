package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.MotionSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;

public interface MotionSensorService {
    ResponseStatusDTO alert(final AlertRequestDTO alertRequestDTO);

    MotionSensorConfigurationDTO getConfiguration(SensorConfigurationRequestDTO motionSensorConfigurationRequestDTO);

}
