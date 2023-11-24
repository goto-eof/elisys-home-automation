package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.response.AliveDTO;
import com.andreidodu.elisyshomeautomation.model.Alive;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AliveMapper {
    AliveDTO toDTO(Alive alive);
    Alive toModel (AliveDTO dto);
}
