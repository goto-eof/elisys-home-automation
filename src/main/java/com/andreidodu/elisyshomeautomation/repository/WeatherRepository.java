package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends CrudRepository<Weather, Long> {

    List<Weather> findByCreatedDateBetweenAndDevice_macAddress(Date startDate, Date endDate, String macAddress);

    Weather findTopByDevice_MacAddressOrderByIdDesc(String macAddress);

    Optional<Weather> findTopByDevice_macAddressAndCreatedDateBetweenAndTemperatureNotNullOrderByTemperatureAsc(String macAddress, Date dateStart, Date dateEnd);

    Optional<Weather> findTopByDevice_macAddressAndCreatedDateBetweenAndTemperatureNotNullOrderByTemperatureDesc(String macAddress, Date dateStart, Date dateEnd);
}