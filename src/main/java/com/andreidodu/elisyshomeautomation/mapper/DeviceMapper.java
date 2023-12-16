package com.andreidodu.elisyshomeautomation.mapper;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceMapper {


    DeviceDTO toDTO(Device model);

    List<DeviceDTO> toDTO(List<Device> model);

    Device toModel(DeviceDTO dto);

    void update(@MappingTarget Device entity, DeviceDTO dto);

}
