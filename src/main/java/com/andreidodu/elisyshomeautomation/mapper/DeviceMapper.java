package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeviceMapper {


    DeviceDTO toDTO(Device model);

    @Mapping(target = "alive", ignore = true)
    @Mapping(target = "motionSensorConfiguration", ignore = true)
    @Mapping(target = "weatherSensorConfiguration", ignore = true)
    @Mapping(target = "weatherList", ignore = true)
    Device toModel(DeviceDTO dto);
}
