package com.andreidodu.elisyshomeautomation.dto.response;

import com.andreidodu.elisyshomeautomation.dto.common.SensorResponseCommonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherSummaryDTO extends SensorResponseCommonDTO {

    private Double lastTemperature;
    private Double lastHumidity;
    private Double minTemperature;
    private Double minHumidity;
    private Double maxTemperature;
    private Double maxHumidity;
    private Double avgTemperature;
    private Double avgHumidity;
    private Double minLux;
    private Double lastLux;
    private Double maxLux;
    private Double avgLux;

    public WeatherSummaryDTO(String macAddress, Double lastTemperature, Double lastHumidity, Double minTemperature, Double minHumidity, Double maxTemperature, Double maxHumidity, Double avgTemperature, Double avgHumidity, Double minLux, Double lastLux, Double maxLux, Double avgLux) {
        super(macAddress);
        this.lastTemperature = lastTemperature;
        this.lastHumidity = lastHumidity;
        this.minTemperature = minTemperature;
        this.minHumidity = minHumidity;
        this.maxTemperature = maxTemperature;
        this.maxHumidity = maxHumidity;
        this.avgTemperature = avgTemperature;
        this.avgHumidity = avgHumidity;
        this.minLux = minLux;
        this.lastLux = lastLux;
        this.maxLux = maxLux;
        this.avgLux = avgLux;
    }
}
