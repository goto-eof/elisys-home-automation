package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.DeviceDTO;
import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.request.DeviceRegistrationDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherByDateRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherDTO;
import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.model.DeviceType;

import java.util.List;

public interface DeviceService {

    DeviceDTO createNewDevice(DeviceType type, String macAddress, String name, String description);

    ResponseStatusDTO register(DeviceRegistrationDTO deviceRegistrationDTO);

    List<DeviceDTO> retrieveDevicesByType(DeviceType type);

    DeviceDTO retrieveDevice(SensorRequestCommonDTO sensorRequestCommonDTO);

    DeviceDTO update(Long id, DeviceDTO dto);

}
