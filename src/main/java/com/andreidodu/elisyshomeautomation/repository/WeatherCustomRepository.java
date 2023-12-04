package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.QWeather;
import com.andreidodu.elisyshomeautomation.model.Weather;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@RequiredArgsConstructor
public class WeatherCustomRepository {

    private final EntityManager entityManager;

    public Weather findMinTemperatureByDateBetween(final String macAddress, Date dateStart, Date dateEnd) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QWeather qWeather = QWeather.weather;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qWeather.device.macAddress.eq(macAddress));
        booleanBuilder.and(qWeather.createdDate.goe(dateStart));
        booleanBuilder.and(qWeather.createdDate.loe(dateEnd));
        booleanBuilder.and(qWeather.temperature.isNotNull());
        return queryFactory
                .select(qWeather)
                .from(qWeather)
                .where(booleanBuilder)
                .orderBy(qWeather.temperature.asc())
                .fetchOne();
    }
}
