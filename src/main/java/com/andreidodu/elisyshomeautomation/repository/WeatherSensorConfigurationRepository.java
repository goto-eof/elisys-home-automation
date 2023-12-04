package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.WeatherSensorConfiguration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WeatherSensorConfigurationRepository extends CrudRepository<WeatherSensorConfiguration, Long> {
    Optional<WeatherSensorConfiguration> findByDevice_MacAddress(String macAddress);
}