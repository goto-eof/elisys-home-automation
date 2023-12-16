package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.response.MotionSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.entity.MotionSensorConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SensorConfigurationMapper {
    @Mapping(source = "device.macAddress", target = "macAddress")
    MotionSensorConfigurationDTO toDTO(MotionSensorConfiguration alive);
    MotionSensorConfiguration toModel(MotionSensorConfigurationDTO dto);
}
