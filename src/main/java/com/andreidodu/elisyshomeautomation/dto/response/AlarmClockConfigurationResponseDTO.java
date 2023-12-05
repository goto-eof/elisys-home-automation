package com.andreidodu.elisyshomeautomation.dto.response;

import com.andreidodu.elisyshomeautomation.dto.common.SensorResponseCommonDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
public class AlarmClockConfigurationResponseDTO extends SensorResponseCommonDTO {
    private List<String> crons;
    private int timezoneSeconds;
    private int alarmIntervalMinutes;
}
