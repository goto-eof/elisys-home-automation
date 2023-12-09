package com.andreidodu.elisyshomeautomation.dto.common;

import com.andreidodu.elisyshomeautomation.model.DeviceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorRequestCommonDTO {
    private String macAddress;
    private DeviceType type;
}
