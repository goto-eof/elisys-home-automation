package com.andreidodu.elisyshomeautomation.dto.response;

import com.andreidodu.elisyshomeautomation.dto.common.SensorResponseCommonDTO;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class WeatherSummaryDTO extends SensorResponseCommonDTO {

    private Double minTemperature;
    private Double lastTemperature;
    private Double maxTemperature;
    private Double avgTemperature;
    private Date minTemperatureDate;
    private Date lastTemperatureDate;
    private Date maxTemperatureDate;

    private Double minHumidity;
    private Double lastHumidity;
    private Double maxHumidity;
    private Double avgHumidity;
    private Date minHumidityDate;
    private Date lastHumidityDate;
    private Date maxHumidityDate;

    private Double minLux;
    private Double lastLux;
    private Double maxLux;
    private Double avgLux;
    private Date minLuxDate;
    private Date lastLuxDate;
    private Date maxLuxDate;

}
