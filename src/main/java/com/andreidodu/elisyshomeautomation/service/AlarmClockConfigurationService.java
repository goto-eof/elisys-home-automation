package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.request.AlarmClockConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationResponseDTO;
import org.springframework.transaction.annotation.Transactional;

public interface AlarmClockConfigurationService {
    AlarmClockConfigurationResponseDTO getConfigurationWithUpdateIAmAlive(AlarmClockConfigurationRequestDTO configurationRequestDTO, boolean updateIAmAlive);

    AlarmClockConfigurationResponseDTO update(Long id, AlarmClockConfigurationResponseDTO dto);
}
