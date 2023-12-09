package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.dto.request.DeviceRegistrationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import com.andreidodu.elisyshomeautomation.model.DeviceType;

import java.util.List;

public interface DeviceService {

    DeviceDTO createNewDevice(DeviceType type, String macAddress, String name, String description);

    ResponseStatusDTO register(DeviceRegistrationDTO deviceRegistrationDTO);

    // TODO in the future filter also by owner (user)
    List<DeviceDTO> retrieveDevicesByType(DeviceType type);
}
