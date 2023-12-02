package com.andreidodu.elisyshomeautomation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceDTO {

    private Long id;
    private String macAddress;
    private String name;
    private String description;
}
