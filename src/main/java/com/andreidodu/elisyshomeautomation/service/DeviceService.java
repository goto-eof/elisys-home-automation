package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;

public interface DeviceService {
    DeviceDTO createNewDevice(String macAddress, String description);
}
