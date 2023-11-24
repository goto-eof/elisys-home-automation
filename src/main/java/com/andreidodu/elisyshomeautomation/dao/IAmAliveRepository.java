package com.andreidodu.elisyshomeautomation.dao;

import com.andreidodu.elisyshomeautomation.model.Alive;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IAmAliveRepository extends CrudRepository<Alive, Long> {
    Optional<Alive> findByMacAddress(String macAddress);
}