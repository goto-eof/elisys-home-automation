package com.andreidodu.elisyshomeautomation.dto.response;

import com.andreidodu.elisyshomeautomation.dto.common.SensorResponseCommonDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class WeatherDTO extends SensorResponseCommonDTO {
    private Long id;
    private Double temperature;
    private Double humidity;
    private Double pressure;
    private Boolean light;
    private Double lux;
    private Date createdDate;
}
