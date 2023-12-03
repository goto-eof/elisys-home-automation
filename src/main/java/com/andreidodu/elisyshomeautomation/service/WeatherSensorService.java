package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherSensorConfigurationDTO;

import java.util.Date;
import java.util.List;

public interface WeatherSensorService {
    WeatherDTO insert(WeatherDTO dto);

    List<WeatherDTO> getAllByDate(String macAddress, Date date);

    WeatherDTO calculateAverageByDate(String macAddress, Date date);

    WeatherDTO calculateAverageByIntervalDate(String macAddress, Date dateStart, Date dateEnd);

    WeatherDTO getMinimumTemperature(String macAddress, Date date);

    WeatherDTO getMaximumTemperature(String macAddress, Date date);

    WeatherDTO getLast(String macAddress);

    WeatherSensorConfigurationDTO getConfiguration(SensorConfigurationRequestDTO motionSensorConfigurationRequestDTO);

}
