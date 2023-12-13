package com.andreidodu.elisyshomeautomation.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorResponseCommonDTO {
    private String macAddress;
    public SensorResponseCommonDTO(String macAddress) {
        this.macAddress = macAddress;
    }
}
