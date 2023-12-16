package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.response.WeatherSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.entity.WeatherSensorConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeatherSensorConfigurationMapper {
    @Mapping(source = "device.macAddress", target = "macAddress")
    WeatherSensorConfigurationDTO toDTO(WeatherSensorConfiguration model);

    @Mapping(target = "device", ignore = true)
    WeatherSensorConfiguration toModel(WeatherSensorConfigurationDTO dto);
}
