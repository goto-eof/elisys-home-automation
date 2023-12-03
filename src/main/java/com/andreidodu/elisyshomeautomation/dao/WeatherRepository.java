package com.andreidodu.elisyshomeautomation.dao;

import com.andreidodu.elisyshomeautomation.model.MotionDetection;
import com.andreidodu.elisyshomeautomation.model.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends CrudRepository<Weather, Long> {

    List<Weather> findByCreatedDateBetweenAndDevice_macAddress(Date startDate, Date endDate, String macAddress);

    Weather findTopByDevice_MacAddressOrderByIdDesc(String macAddress);

    Optional<Weather> findTopByDevice_macAddressAndCreatedDateBetweenOrderByTemperatureAsc(String macAddress, Date dateStart, Date dateEnd);

    Optional<Weather> findTopByDevice_macAddressAndCreatedDateBetweenOrderByTemperatureDesc(String macAddress, Date dateStart, Date dateEnd);
}