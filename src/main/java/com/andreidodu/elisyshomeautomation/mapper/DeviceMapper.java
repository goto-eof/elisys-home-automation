package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherDTO;
import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.model.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceMapper {


    DeviceDTO toDTO(Device model);

    @Mapping(target = "alive",ignore = true)
    @Mapping(target = "sensorConfiguration",ignore = true)
    @Mapping(target = "weather",ignore = true)
    Device toModel(DeviceDTO dto);
}
