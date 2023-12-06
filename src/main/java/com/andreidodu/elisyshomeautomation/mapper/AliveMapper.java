package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.response.AliveDTO;
import com.andreidodu.elisyshomeautomation.model.Alive;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AliveMapper {
    @Mapping(target = "macAddress", source = "device.macAddress")
    AliveDTO toDTO(Alive alive);

    @Mapping(target = "device", ignore = true)
    Alive toModel(AliveDTO dto);
}
