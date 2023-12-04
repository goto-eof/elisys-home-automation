package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.RelayConfiguration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RelayConfigurationRepository extends CrudRepository<RelayConfiguration, Long> {

    Optional<RelayConfiguration> findByDevice_MacAddress(String macAddress);
}