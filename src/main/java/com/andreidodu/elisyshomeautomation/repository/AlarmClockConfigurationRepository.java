package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.AlarmClockConfiguration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AlarmClockConfigurationRepository extends CrudRepository<AlarmClockConfiguration, Long> {

    Optional<AlarmClockConfiguration> findByDevice_MacAddress(String macAddress);
}