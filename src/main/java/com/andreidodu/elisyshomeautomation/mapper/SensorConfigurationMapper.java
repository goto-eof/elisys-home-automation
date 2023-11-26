package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.response.SensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.model.SensorConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SensorConfigurationMapper {
    @Mapping(source = "device.macAddress", target = "macAddress")
    SensorConfigurationDTO toDTO(SensorConfiguration alive);
    SensorConfiguration toModel(SensorConfigurationDTO dto);
}
