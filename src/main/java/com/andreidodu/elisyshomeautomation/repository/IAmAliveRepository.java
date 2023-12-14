package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.Alive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAmAliveRepository extends JpaRepository<Alive, Long> {
    Optional<Alive> findByDevice_MacAddress(String macAddress);
}