package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.model.AlarmClockConfiguration;
import com.andreidodu.elisyshomeautomation.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceMapper {


    DeviceDTO toDTO(Device model);

    List<DeviceDTO> toDTO(List<Device> model);

    @Mapping(target = "alive", ignore = true)
    @Mapping(target = "motionSensorConfiguration", ignore = true)
    @Mapping(target = "weatherSensorConfiguration", ignore = true)
    @Mapping(target = "weatherList", ignore = true)
    Device toModel(DeviceDTO dto);

    void update(@MappingTarget Device entity, DeviceDTO dto);

}
