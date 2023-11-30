package com.andreidodu.elisyshomeautomation.dto.request;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherDTO extends SensorRequestCommonDTO {

    private Long id;
    private Double temperature;
    private Double humidity;
    private Double pressure;
}
