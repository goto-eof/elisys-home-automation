package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.MotionSensorConfiguration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MotionSensorConfigurationRepository extends CrudRepository<MotionSensorConfiguration, Long> {
    Optional<MotionSensorConfiguration> findByDevice_MacAddress(String macAddress);
}