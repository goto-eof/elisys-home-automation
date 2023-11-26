package com.andreidodu.elisyshomeautomation.dao;

import com.andreidodu.elisyshomeautomation.model.SensorConfiguration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SensorConfigurationRepository extends CrudRepository<SensorConfiguration, Long> {
    Optional<SensorConfiguration> findByDevice_MacAddress(String macAddress);
}