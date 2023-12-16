package com.andreidodu.elisyshomeautomation.repository.impl;

import com.andreidodu.elisyshomeautomation.entity.QWeather;
import com.andreidodu.elisyshomeautomation.entity.Weather;
import com.andreidodu.elisyshomeautomation.repository.WeatherCustomRepository;
import com.andreidodu.elisyshomeautomation.util.DateUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WeatherCustomRepositoryImpl implements WeatherCustomRepository {

    private final EntityManager entityManager;

    public Optional<Weather> findTemperatureByDateBetween(final String macAddress, Date dateStart, Date dateEnd, OrderSpecifier<Double> order) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QWeather qWeather = QWeather.weather;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qWeather.device.macAddress.eq(macAddress));
        booleanBuilder.and(qWeather.createdDate.goe(DateUtil.toLocalDateTime(dateStart)));
        booleanBuilder.and(qWeather.createdDate.loe(DateUtil.toLocalDateTime(dateEnd)));
        booleanBuilder.and(qWeather.temperature.isNotNull());
        return Optional.ofNullable(queryFactory
                .select(qWeather)
                .from(qWeather)
                .where(booleanBuilder)
                .orderBy(order)
                .fetchFirst());
    }

    public Optional<Weather> findMaxTemperatureByDateBetween(final String macAddress, final Date dateStart, final Date dateEnd) {
        OrderSpecifier<Double> order = QWeather.weather.temperature.desc();
        return findTemperatureByDateBetween(macAddress, dateStart, dateEnd, order);
    }

    public Optional<Weather> findMinTemperatureByDateBetween(final String macAddress, final Date dateStart, final Date dateEnd) {
        OrderSpecifier<Double> order = QWeather.weather.temperature.asc();
        return findTemperatureByDateBetween(macAddress, dateStart, dateEnd, order);
    }

    public Optional<Weather> findMaxHumidityByDateBetween(final String macAddress, final Date dateStart, final Date dateEnd) {
        OrderSpecifier<Double> order = QWeather.weather.humidity.desc();
        return findTemperatureByDateBetween(macAddress, dateStart, dateEnd, order);
    }

    public Optional<Weather> findMinHumidityByDateBetween(final String macAddress, final Date dateStart, final Date dateEnd) {
        OrderSpecifier<Double> order = QWeather.weather.humidity.asc();
        return findTemperatureByDateBetween(macAddress, dateStart, dateEnd, order);
    }
}
