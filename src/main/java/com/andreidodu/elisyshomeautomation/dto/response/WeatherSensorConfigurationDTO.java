package com.andreidodu.elisyshomeautomation.dto.response;

import com.andreidodu.elisyshomeautomation.dto.common.SensorResponseCommonDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherSensorConfigurationDTO extends SensorResponseCommonDTO {

    private Long id;
    private String alertEndpoint;
    @JsonProperty("iAmAliveEndpoint")
    private String iAmAliveEndpoint;
    @JsonProperty("iAmAliveIntervalSeconds")
    private Long iAmAliveIntervalSeconds;
    private String temperatureSensorUnitOfMeasure;
    private Long weatherSensorSupplyIntervalSeconds;
    private String crontab;

}
