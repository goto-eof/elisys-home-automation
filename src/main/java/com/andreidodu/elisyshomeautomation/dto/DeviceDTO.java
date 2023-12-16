package com.andreidodu.elisyshomeautomation.dto;

import com.andreidodu.elisyshomeautomation.entity.DeviceType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeviceDTO {

    private Long id;
    private String macAddress;
    private String name;
    private String description;
    private DeviceType type;
    private LocalDateTime lastAck;

}
