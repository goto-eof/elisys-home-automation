package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationCronResponseDTO;
import com.andreidodu.elisyshomeautomation.dto.response.AlarmClockConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.entity.AlarmClockConfiguration;
import com.andreidodu.elisyshomeautomation.entity.AlarmClockConfigurationCron;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AlarmClockConfigurationMapper {

    @Mapping(target = "macAddress", source = "device.macAddress")
    AlarmClockConfigurationResponseDTO toDTO(AlarmClockConfiguration model);

    @Mapping(target = "device", ignore = true)
    AlarmClockConfiguration toModel(AlarmClockConfigurationResponseDTO dto);

    @Mapping(target = "configuration", ignore = true)
    AlarmClockConfigurationCron toModel(AlarmClockConfigurationCronResponseDTO dto);

    @Mapping(target = "configuration", ignore = true)
    void updateCron(@MappingTarget AlarmClockConfigurationCron entity, AlarmClockConfigurationCronResponseDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "cronList", ignore = true)
    @Mapping(target = "device", ignore = true)
    void update(@MappingTarget AlarmClockConfiguration entity, AlarmClockConfigurationResponseDTO dto);

}
