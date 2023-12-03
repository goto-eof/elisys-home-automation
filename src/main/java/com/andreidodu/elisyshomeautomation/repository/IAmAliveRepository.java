package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.Alive;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IAmAliveRepository extends CrudRepository<Alive, Long> {
    Optional<Alive> findByDevice_MacAddress(String macAddress);
}