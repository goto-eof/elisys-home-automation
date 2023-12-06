package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.request.AlarmClockConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationResponseDTO;

public interface AlarmClockConfigurationService {
    AlarmClockConfigurationResponseDTO getConfiguration(AlarmClockConfigurationRequestDTO configurationRequestDTO);
}
