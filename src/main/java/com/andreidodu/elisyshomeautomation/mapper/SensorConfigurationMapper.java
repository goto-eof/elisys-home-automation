package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.response.SensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.model.SensorConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SensorConfigurationMapper {
    SensorConfigurationDTO toDTO(SensorConfiguration alive);
    SensorConfiguration toModel(SensorConfigurationDTO dto);
}
