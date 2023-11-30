package com.andreidodu.elisyshomeautomation.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorResponseCommonDTO extends SensorRequestCommonDTO {
    private String macAddress;
}
