package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;

public interface DeviceService {
    DeviceDTO createNewDevice(final String macAddress, final String name, final String description);
}
