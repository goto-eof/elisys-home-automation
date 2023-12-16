package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dto.common.SensorRequestCommonDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherSummaryDTO;
import com.andreidodu.elisyshomeautomation.entity.DeviceType;
import com.andreidodu.elisyshomeautomation.repository.DeviceRepository;
import com.andreidodu.elisyshomeautomation.repository.WeatherCustomRepository;
import com.andreidodu.elisyshomeautomation.repository.WeatherRepository;
import com.andreidodu.elisyshomeautomation.repository.WeatherSensorConfigurationRepository;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.exception.ApplicationException;
import com.andreidodu.elisyshomeautomation.mapper.WeatherMapper;
import com.andreidodu.elisyshomeautomation.mapper.WeatherSensorConfigurationMapper;
import com.andreidodu.elisyshomeautomation.entity.Device;
import com.andreidodu.elisyshomeautomation.entity.Weather;
import com.andreidodu.elisyshomeautomation.entity.WeatherSensorConfiguration;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.IAmAliveService;
import com.andreidodu.elisyshomeautomation.service.WeatherSensorService;
import com.andreidodu.elisyshomeautomation.util.DateUtil;
import com.andreidodu.elisyshomeautomation.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WeatherSensorServiceImpl implements WeatherSensorService {

    public static final String DEVICE_NAME = "weather sensor";

    @Value("${app.configuration.default.max.chart.elements.24h}")
    private int maxChartElements24h;

    @Value("${app.configuration.default.max.chart.elements.7d}")
    private int maxChartElements7d;

    @Value("${app.configuration.default.max.chart.elements.1m}")
    private int maxChartElements1m;
    @Value("${app.configuration.default.temperature.sensor.unit.of.measure}")
    private String temperatureSensorUnitOfMeasure;

    @Value("${app.configuration.default.weather.sensors.supply.interval.seconds}")
    private Long weatherSensorSupplyIntervalSeconds;
    @Value("${app.configuration.default.weather.sensor.iamalive.interval.seconds}")
    private Long iAmAliveIntervalSeconds;

    @Value("${app.configuration.default.weather.sensor.iamalive.endpoint}")
    private String iAmAliveEndpoint;

    @Value("${app.configuration.default.weather.sensor.alert.endpoint}")
    private String alertEndpoint;

    @Value("${app.configuration.default.weather.sensor.crontab}")
    private String crontab;

    @Value("${app.configuration.default.weather.sensor.crontab.timezone}")
    private Integer timezoneOffsetSec;

    private final WeatherSensorConfigurationRepository sensorConfigurationRepository;
    private final WeatherSensorConfigurationMapper sensorConfigurationMapper;
    private final DeviceRepository deviceRepository;
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;
    private final DeviceService deviceService;
    private final WeatherCustomRepository weatherCustomRepository;
    private final IAmAliveService iAmAliveService;

    @Override
    public WeatherDTO insert(final WeatherDTO dto) {
        Weather model = this.weatherMapper.toModel(dto);
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(dto.getMacAddress());
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(DeviceType.WeatherStation, dto.getMacAddress(), DEVICE_NAME, dto.getMacAddress());
            deviceOptional = this.deviceRepository.findByMacAddress(dto.getMacAddress());
        }
        model.setDevice(deviceOptional.get());
        Weather savedModel = this.weatherRepository.save(model);
        iAmAliveService.updateByMacAddress(dto.getMacAddress());
        return this.weatherMapper.toDTO(savedModel);
    }


    @Override
    public List<WeatherDTO> getAllByDate(final String macAddress, final Date dateStart, final Date dateEnd, Optional<Integer> numberOfItemsToBeExtracted) {
        if (numberOfItemsToBeExtracted.isPresent() && numberOfItemsToBeExtracted.get() == 0) {
            throw new ApplicationException("Invalid input: numberOfItemsToBeExtracted = 0");
        }

        List<Weather> weatherList = this.weatherRepository.findByCreatedDateBetweenAndDevice_macAddress(dateStart, dateEnd, macAddress);
        if (numberOfItemsToBeExtracted.isEmpty()) {
            return this.weatherMapper.toDTO(weatherList);
        }

        long length = weatherList.size();
        long slice = length / (numberOfItemsToBeExtracted.get() - 1);
        if (slice > 0) {
            List<Weather> wheatherListFinal = IntStream.range(0, weatherList.size())
                    .filter(i -> i % slice == 0)
                    .mapToObj(weatherList::get)
                    .toList();
            if (!weatherList.isEmpty() && wheatherListFinal.get(wheatherListFinal.size() - 1) != weatherList.get(weatherList.size() - 1)) {
                wheatherListFinal = new ArrayList<>(wheatherListFinal);
                wheatherListFinal.add(weatherList.get(weatherList.size() - 1));
            }
            return this.weatherMapper.toDTO(wheatherListFinal);
        }
        return this.weatherMapper.toDTO(weatherList);
    }

    private List<WeatherDTO> getAllByDateInterval(final String macAddress, final Date dateStart, Date dateEnd) {
        List<Weather> weatherList = this.weatherRepository.findByCreatedDateBetweenAndDevice_macAddress(dateStart, dateEnd, macAddress);
        return this.weatherMapper.toDTO(weatherList);
    }

    @Override
    public WeatherDTO calculateAverageByDate(final String macAddress, final Date date) {
        Date startDate = DateUtil.calculateStartDate(date);
        Date endDate = DateUtil.calculateEndDate(date);
        List<WeatherDTO> dtoList = this.getAllByDate(macAddress, startDate, endDate, Optional.empty());
        WeatherDTO weatherDTO = calculateAverageFromList(dtoList);
        weatherDTO.setMacAddress(macAddress);
        return weatherDTO;
    }

    @Override
    public WeatherDTO calculateAverageByIntervalDate(String macAddress, Date dateStart, Date dateEnd) {
        List<WeatherDTO> dtoList = this.getAllByDateInterval(macAddress, dateStart, dateEnd);
        WeatherDTO weatherDTO = calculateAverageFromList(dtoList);
        weatherDTO.setMacAddress(macAddress);
        return weatherDTO;
    }


    @Override
    public WeatherSummaryDTO retrieveSummary(String macAddress, Date dateStart, Date dateEnd) {
        List<WeatherDTO> dtoList = this.getAllByDateInterval(macAddress, dateStart, dateEnd);
        WeatherDTO averageDTO = calculateAverageFromList(dtoList);
        WeatherDTO last = getLast(macAddress);
        WeatherDTO minTemperatureDTO = calculateMinimumTemperature(dtoList);
        WeatherDTO minHumidityDTO = calculateMinimumHumidity(dtoList);
        WeatherDTO maxTemperatureDTO = calculateMaximumTemperature(dtoList);
        WeatherDTO maxHumidityDTO = calculateMaximumHumidity(dtoList);
        WeatherDTO maxLuxDTO = calculateMaximumLux(dtoList);
        WeatherDTO minLuxDTO = calculateMinimumLux(dtoList);
        final Double avgTemperature = NumberUtil.normalize(averageDTO.getTemperature());
        final Double avgHumidity = NumberUtil.normalize(averageDTO.getHumidity());
        final Double minTemperature = NumberUtil.normalize(minTemperatureDTO.getTemperature());
        final Double minHumidity = NumberUtil.normalize(minHumidityDTO.getHumidity());
        final Double maxTemperature = NumberUtil.normalize(maxTemperatureDTO.getTemperature());
        final Double maxHumidity = NumberUtil.normalize(maxHumidityDTO.getHumidity());
        final Double avgLux = NumberUtil.normalize(averageDTO.getLux());
        final Double maxLux = NumberUtil.normalize(maxLuxDTO.getLux());
        final Double minLux = NumberUtil.normalize(minLuxDTO.getLux());
        final Double lastLux = last.getLux();
        final Double lastTemperature = NumberUtil.normalize(last.getTemperature());
        final Double lastHumidity = NumberUtil.normalize(last.getHumidity());
        WeatherSummaryDTO weatherSummaryDTO = WeatherSummaryDTO
                .builder()
                .lastTemperature(lastTemperature)
                .lastHumidity(lastHumidity)
                .lastLux(lastLux)
                .minTemperature(minTemperature)
                .minHumidity(minHumidity)
                .minLux(minLux)
                .maxTemperature(maxTemperature)
                .maxHumidity(maxHumidity)
                .maxLux(maxLux)
                .avgTemperature(avgTemperature)
                .avgHumidity(avgHumidity)
                .avgLux(avgLux)
                .build();

        weatherSummaryDTO.setMacAddress(macAddress);

        weatherSummaryDTO.setMinTemperatureDate(minTemperatureDTO.getCreatedDate());
        weatherSummaryDTO.setMinHumidityDate(minHumidityDTO.getCreatedDate());
        weatherSummaryDTO.setMinLuxDate(minLuxDTO.getCreatedDate());

        weatherSummaryDTO.setLastTemperatureDate(last.getCreatedDate());
        weatherSummaryDTO.setLastHumidityDate(last.getCreatedDate());
        weatherSummaryDTO.setLastLuxDate(last.getCreatedDate());

        weatherSummaryDTO.setMaxTemperatureDate(maxTemperatureDTO.getCreatedDate());
        weatherSummaryDTO.setMaxHumidityDate(maxHumidityDTO.getCreatedDate());
        weatherSummaryDTO.setMaxLuxDate(maxLuxDTO.getCreatedDate());

        return weatherSummaryDTO;

    }


    private WeatherDTO calculateMinimumLux(List<WeatherDTO> dtoList) {
        return dtoList.stream()
                .filter(item -> item.getLux() != null)
                .min(Comparator.comparingDouble(WeatherDTO::getLux))
                .orElse(new WeatherDTO());
    }

    private WeatherDTO calculateMaximumLux(List<WeatherDTO> dtoList) {
        return dtoList.stream()
                .filter(item -> item.getLux() != null)
                .max(Comparator.comparingDouble(WeatherDTO::getLux))
                .orElse(new WeatherDTO());
    }

    private WeatherDTO calculateMinimumTemperature(List<WeatherDTO> dtoList) {
        return dtoList.stream()
                .filter(item -> item.getTemperature() != null)
                .min(Comparator.comparingDouble(WeatherDTO::getTemperature))
                .orElse(new WeatherDTO());
    }

    private WeatherDTO calculateMaximumTemperature(List<WeatherDTO> dtoList) {
        return dtoList.stream()
                .filter(item -> item.getTemperature() != null)
                .max(Comparator.comparingDouble(WeatherDTO::getTemperature))
                .orElse(new WeatherDTO());
    }

    private WeatherDTO calculateMinimumHumidity(List<WeatherDTO> dtoList) {
        return dtoList.stream()
                .filter(item -> item.getHumidity() != null)
                .min(Comparator.comparingDouble(WeatherDTO::getHumidity))
                .orElse(new WeatherDTO());
    }

    private WeatherDTO calculateMaximumHumidity(List<WeatherDTO> dtoList) {
        return dtoList.stream()
                .filter(item -> item.getHumidity() != null)
                .max(Comparator.comparingDouble(WeatherDTO::getHumidity))
                .orElse(new WeatherDTO());
    }


    @Override
    public WeatherDTO getMinimumTemperature(final String macAddress, final Date date) {
        Date dateStart = DateUtil.calculateStartDate(date);
        Date dateEnd = DateUtil.calculateEndDate(date);
        log.info("mac_address: " + macAddress + " - dateStart: " + dateStart + " - dateEnd: " + dateEnd);
        Optional<Weather> weatherOptional = this.weatherCustomRepository.findMinTemperatureByDateBetween(macAddress, dateStart, dateEnd);
        return weatherOptional.map(weatherMapper::toDTO).orElseThrow(() -> new ApplicationException("no record found"));
    }

    @Override
    public WeatherDTO getMaximumTemperature(final String macAddress, final Date date) {
        Date dateStart = DateUtil.calculateStartDate(date);
        Date dateEnd = DateUtil.calculateEndDate(date);
        log.info("mac_address: " + macAddress + " - dateStart: " + dateStart + " - dateEnd: " + dateEnd);
        Optional<Weather> weatherOptional = this.weatherCustomRepository.findMaxTemperatureByDateBetween(macAddress, dateStart, dateEnd);
        return weatherOptional.map(weatherMapper::toDTO).orElseThrow(() -> new ApplicationException("no record found"));
    }

    @Override
    public WeatherDTO getMinimumHumidity(final String macAddress, final Date date) {
        Date dateStart = DateUtil.calculateStartDate(date);
        Date dateEnd = DateUtil.calculateEndDate(date);
        log.info("mac_address: " + macAddress + " - dateStart: " + dateStart + " - dateEnd: " + dateEnd);
        Optional<Weather> weatherOptional = this.weatherCustomRepository.findMinHumidityByDateBetween(macAddress, dateStart, dateEnd);
        return weatherOptional.map(weatherMapper::toDTO).orElseThrow(() -> new ApplicationException("no record found"));
    }

    @Override
    public WeatherDTO getMaximumHumidity(final String macAddress, final Date date) {
        Date dateStart = DateUtil.calculateStartDate(date);
        Date dateEnd = DateUtil.calculateEndDate(date);
        log.info("mac_address: " + macAddress + " - dateStart: " + dateStart + " - dateEnd: " + dateEnd);
        Optional<Weather> weatherOptional = this.weatherCustomRepository.findMaxHumidityByDateBetween(macAddress, dateStart, dateEnd);
        return weatherOptional.map(weatherMapper::toDTO).orElseThrow(() -> new ApplicationException("no record found"));
    }

    private static WeatherDTO calculateAverageFromList(List<WeatherDTO> dtoList) {
        double humidity = 0.0;
        double temperature = 0.0;
        double pressure = 0.0;
        double lux = 0.0;
        int humidityCount = 0;
        int temperatureCount = 0;
        int pressureCount = 0;
        int luxCount = 0;
        for (WeatherDTO weatherDTO : dtoList) {
            if (weatherDTO.getHumidity() != null) {
                humidityCount++;
                humidity += weatherDTO.getHumidity();
            }
            if (weatherDTO.getTemperature() != null) {
                temperatureCount++;
                temperature += weatherDTO.getTemperature();
            }
            if (weatherDTO.getPressure() != null) {
                pressureCount++;
                pressure += weatherDTO.getPressure();
            }
            if (weatherDTO.getLux() != null) {
                luxCount++;
                lux += weatherDTO.getLux();
            }
        }
        WeatherDTO result = new WeatherDTO();
        result.setHumidity(NumberUtil.normalize(humidity / humidityCount));
        result.setTemperature(NumberUtil.normalize(temperature / temperatureCount));
        result.setPressure(NumberUtil.normalize(pressure / pressureCount));
        result.setLux(NumberUtil.normalize(lux / luxCount));
        return result;
    }

    @Override
    public WeatherDTO getLast(final String macAddress) {
        Optional<Weather> weather = this.weatherRepository.findTopByDevice_MacAddressOrderByIdDesc(macAddress);
        return this.weatherMapper.toDTO(weather.orElse(new Weather()));
    }


    @Override
    public WeatherSensorConfigurationDTO getConfiguration(SensorConfigurationRequestDTO sensorConfigurationRequestDTO) {
        Optional<WeatherSensorConfiguration> sensorConfigurationOptional = sensorConfigurationRepository.findByDevice_MacAddress(sensorConfigurationRequestDTO.getMacAddress());
        if (sensorConfigurationOptional.isPresent()) {
            WeatherSensorConfigurationDTO result = sensorConfigurationMapper.toDTO(sensorConfigurationOptional.get());
            log.info(result.toString());
            return result;
        }
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(sensorConfigurationRequestDTO.getMacAddress());
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(DeviceType.WeatherStation, sensorConfigurationRequestDTO.getMacAddress(), DEVICE_NAME, sensorConfigurationRequestDTO.getMacAddress());
            deviceOptional = this.deviceRepository.findByMacAddress(sensorConfigurationRequestDTO.getMacAddress());
        }
        WeatherSensorConfigurationDTO dto = loadDefaultConfiguration(sensorConfigurationRequestDTO.getMacAddress());
        WeatherSensorConfiguration model = sensorConfigurationMapper.toModel(dto);
        model.setDevice(deviceOptional.get());
        WeatherSensorConfigurationDTO result = this.sensorConfigurationMapper.toDTO(this.sensorConfigurationRepository.save(model));
        log.info(result.toString());
        return result;
    }

    @Override
    public WeatherSummaryDTO retrieveTodaySummary(String macAddress) {
        return retrieveSummary(macAddress, DateUtil.getTodayDateWithHour(9), DateUtil.getTodayDateWithHour(21));
    }

    @Override
    public WeatherSummaryDTO retrieveYesterdayDaySummary(String macAddress) {
        return retrieveSummary(macAddress, DateUtil.getYesterdayDateWithHour(9), DateUtil.getYesterdayDateWithHour(21));
    }

    @Override
    public WeatherSummaryDTO retrieveLastNightSummary(String macAddress) {
        return retrieveSummary(macAddress, DateUtil.getYesterdayDateWithHour(21), DateUtil.getTodayDateWithHour(9));
    }

    @Override
    public List<WeatherDTO> getLast24h(SensorRequestCommonDTO dto) {
        final Date now = new Date();
        return getAllByDate(dto.getMacAddress(), DateUtil.calculate24hAgo(now), now, Optional.of(maxChartElements24h));
    }

    @Override
    public WeatherSummaryDTO getLast24hSummary(SensorRequestCommonDTO dto) {
        final Date now = new Date();
        return this.retrieveSummary(dto.getMacAddress(), DateUtil.calculate24hAgo(now), now);
    }

    @Override
    public List<WeatherDTO> getLast7d(SensorRequestCommonDTO dto) {
        final Date now = new Date();
        return getAllByDate(dto.getMacAddress(), DateUtil.calculate7dAgo(now), now, Optional.of(maxChartElements7d));
    }

    @Override
    public WeatherSummaryDTO getLast7dSummary(SensorRequestCommonDTO dto) {
        final Date now = new Date();
        return this.retrieveSummary(dto.getMacAddress(), DateUtil.calculate7dAgo(now), now);
    }

    @Override
    public List<WeatherDTO> getLast1m(SensorRequestCommonDTO dto) {
        final Date now = new Date();
        return getAllByDate(dto.getMacAddress(), DateUtil.calculate1mAgo(now), now, Optional.of(maxChartElements1m));
    }

    @Override
    public WeatherSummaryDTO getLast1mSummary(SensorRequestCommonDTO dto) {
        final Date now = new Date();
        return this.retrieveSummary(dto.getMacAddress(), DateUtil.calculate1mAgo(now), now);
    }

    private WeatherSensorConfigurationDTO loadDefaultConfiguration(String macAddress) {
        WeatherSensorConfigurationDTO dto = new WeatherSensorConfigurationDTO();
        dto.setAlertEndpoint(alertEndpoint);
        dto.setMacAddress(macAddress);
        dto.setIAmAliveIntervalSeconds(iAmAliveIntervalSeconds);
        dto.setIAmAliveEndpoint(iAmAliveEndpoint);
        dto.setWeatherSensorSupplyIntervalSeconds(weatherSensorSupplyIntervalSeconds);
        dto.setTemperatureSensorUnitOfMeasure(temperatureSensorUnitOfMeasure);
        dto.setCrontab(crontab);
        dto.setTimezoneOffsetSec(timezoneOffsetSec);
        return dto;
    }


}
