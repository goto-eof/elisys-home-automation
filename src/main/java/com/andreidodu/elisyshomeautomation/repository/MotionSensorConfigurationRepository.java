package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.MotionSensorConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotionSensorConfigurationRepository extends JpaRepository<MotionSensorConfiguration, Long> {
    Optional<MotionSensorConfiguration> findByDevice_MacAddress(String macAddress);
}