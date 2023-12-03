package com.andreidodu.elisyshomeautomation.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WeatherCustomRepository {

    private final EntityManager entityManager;
}
