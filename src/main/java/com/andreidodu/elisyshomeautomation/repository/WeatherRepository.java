package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    List<Weather> findByCreatedDateBetweenAndDevice_macAddress(LocalDateTime startDate, LocalDateTime endDate, String macAddress);

    Optional<Weather> findTopByDevice_MacAddressOrderByIdDesc(String macAddress);

}