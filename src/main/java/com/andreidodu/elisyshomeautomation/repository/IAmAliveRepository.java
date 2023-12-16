package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.entity.Alive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IAmAliveRepository extends JpaRepository<Alive, Long> {
    Optional<Alive> findByDevice_MacAddress(String macAddress);
}