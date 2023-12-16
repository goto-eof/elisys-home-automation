package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.response.RelayConfigurationResponseDTO;
import com.andreidodu.elisyshomeautomation.entity.RelayConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RelayConfigurationMapper {
    @Mapping(source = "device.macAddress", target = "macAddress")
    RelayConfigurationResponseDTO toDTO(RelayConfiguration model);

    RelayConfiguration toModel(RelayConfigurationResponseDTO dto);
}
