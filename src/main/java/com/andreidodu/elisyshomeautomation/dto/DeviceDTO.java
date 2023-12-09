package com.andreidodu.elisyshomeautomation.dto;

import com.andreidodu.elisyshomeautomation.model.DeviceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceDTO {

    private Long id;
    private String macAddress;
    private String name;
    private String description;
    private DeviceType type;

}
