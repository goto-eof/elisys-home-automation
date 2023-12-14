package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.AlarmClockConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AlarmClockConfigurationRepository extends JpaRepository<AlarmClockConfiguration, Long> {

    Optional<AlarmClockConfiguration> findByDevice_MacAddress(String macAddress);
}