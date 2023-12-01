package com.andreidodu.elisyshomeautomation.dto.request;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WeatherByDateIntervalRequestDTO extends SensorRequestCommonDTO {
    private Date dateStart;
    private Date dateEnd;
}
