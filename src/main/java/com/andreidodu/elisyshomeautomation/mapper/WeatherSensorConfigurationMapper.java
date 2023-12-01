package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.response.MotionSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.model.MotionSensorConfiguration;
import com.andreidodu.elisyshomeautomation.model.WeatherSensorConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeatherSensorConfigurationMapper {
    @Mapping(source = "device.macAddress", target = "macAddress")
    WeatherSensorConfigurationDTO toDTO(WeatherSensorConfiguration model);
    WeatherSensorConfiguration toModel(WeatherSensorConfigurationDTO dto);
}
