package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.request.WeatherDTO;

import java.util.Date;
import java.util.List;

public interface WeatherService {
    WeatherDTO insert(WeatherDTO dto);

    List<WeatherDTO> getAllByDate(String macAddress, Date date);

    WeatherDTO calculateAverageByDate(String macAddress, Date date);

    WeatherDTO getLast(String macAddress);
}
