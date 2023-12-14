package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.WeatherSensorConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherSensorConfigurationRepository extends JpaRepository<WeatherSensorConfiguration, Long> {
    Optional<WeatherSensorConfiguration> findByDevice_MacAddress(String macAddress);
}