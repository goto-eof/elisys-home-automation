package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.RelayConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RelayConfigurationRepository extends JpaRepository<RelayConfiguration, Long> {

    Optional<RelayConfiguration> findByDevice_MacAddress(String macAddress);
}