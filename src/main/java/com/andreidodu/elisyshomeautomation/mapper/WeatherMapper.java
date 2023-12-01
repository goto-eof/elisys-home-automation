package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.response.WeatherDTO;
import com.andreidodu.elisyshomeautomation.model.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeatherMapper {


    List<WeatherDTO> toDTO(List<Weather> modelList);

    @Mapping(source = "device.macAddress", target = "macAddress")
    WeatherDTO toDTO(Weather model);

    Weather toModel(WeatherDTO dto);
}
