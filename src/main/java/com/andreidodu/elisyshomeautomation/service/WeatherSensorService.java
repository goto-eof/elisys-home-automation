package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherByDateRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherSummaryDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WeatherSensorService {
    WeatherDTO insert(WeatherDTO dto);


    List<WeatherDTO> getAllByDate(String macAddress, Date dateStart, Date dateEnd, Optional<Integer> numberOfItemsToBeExtracted);

    WeatherDTO calculateAverageByDate(String macAddress, Date date);

    WeatherDTO calculateAverageByIntervalDate(String macAddress, Date dateStart, Date dateEnd);

    WeatherSummaryDTO retrieveSummary(String macAddress, Date dateStart, Date dateEnd);

    WeatherDTO getMinimumTemperature(String macAddress, Date date);

    WeatherDTO getMaximumTemperature(String macAddress, Date date);

    WeatherDTO getMinimumHumidity(String macAddress, Date date);

    WeatherDTO getMaximumHumidity(String macAddress, Date date);

    WeatherDTO getLast(String macAddress);

    WeatherSensorConfigurationDTO getConfiguration(SensorConfigurationRequestDTO motionSensorConfigurationRequestDTO);

    WeatherSummaryDTO retrieveTodaySummary(String macAddress);

    WeatherSummaryDTO retrieveYesterdayDaySummary(String macAddress);

    WeatherSummaryDTO retrieveLastNightSummary(String macAddress);

    List<WeatherDTO> getLast24h(SensorRequestCommonDTO dto);

    WeatherSummaryDTO getLast24hSummary(SensorRequestCommonDTO dto);
}
