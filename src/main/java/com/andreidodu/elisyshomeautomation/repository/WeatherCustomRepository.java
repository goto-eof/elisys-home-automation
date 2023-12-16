package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.entity.Weather;

import java.util.Date;
import java.util.Optional;

public interface WeatherCustomRepository {

     Optional<Weather> findMaxTemperatureByDateBetween (final String macAddress, final Date dateStart, final Date dateEnd);

     Optional<Weather> findMinTemperatureByDateBetween (final String macAddress, final Date dateStart, final Date dateEnd);

     Optional<Weather> findMaxHumidityByDateBetween (final String macAddress, final Date dateStart, final Date dateEnd);

     Optional<Weather> findMinHumidityByDateBetween (final String macAddress, final Date dateStart, final Date dateEnd);
}
