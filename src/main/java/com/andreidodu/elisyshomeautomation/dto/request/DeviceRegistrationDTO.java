package com.andreidodu.elisyshomeautomation.dto.request;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.model.DeviceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceRegistrationDTO extends SensorRequestCommonDTO {
    private DeviceType type;
    private String name;
    private String description;
}
