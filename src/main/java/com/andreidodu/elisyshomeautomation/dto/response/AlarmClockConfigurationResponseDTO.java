package com.andreidodu.elisyshomeautomation.dto.response;

import com.andreidodu.elisyshomeautomation.dto.common.SensorResponseCommonDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AlarmClockConfigurationResponseDTO extends SensorResponseCommonDTO {
    private Long id;
    private List<AlarmClockConfigurationCronResponseDTO> cronList;
    private Long timezoneSeconds;
    private Integer alarmIntervalMinutes;
    private String iAmAliveEndpoint;
    private Long iAmAliveIntervalSeconds;
}
