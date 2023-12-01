package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dao.DeviceRepository;
import com.andreidodu.elisyshomeautomation.dao.WeatherRepository;
import com.andreidodu.elisyshomeautomation.dao.WeatherSensorConfigurationRepository;
import com.andreidodu.elisyshomeautomation.dto.request.SensorConfigurationRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherDTO;
import com.andreidodu.elisyshomeautomation.dto.response.WeatherSensorConfigurationDTO;
import com.andreidodu.elisyshomeautomation.mapper.WeatherMapper;
import com.andreidodu.elisyshomeautomation.mapper.WeatherSensorConfigurationMapper;
import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.model.Weather;
import com.andreidodu.elisyshomeautomation.model.WeatherSensorConfiguration;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.WeatherSensorService;
import com.andreidodu.elisyshomeautomation.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WeatherSensorServiceImpl implements WeatherSensorService {

    @Value("${app.configuration.default.temperature.sensor.unit.of.measure}")
    private String temperatureSensorUnitOfMeasure;

    @Value("${app.configuration.default.weather.sensors.supply.interval.seconds}")
    private Long weatherSensorSupplyIntervalSeconds;
    @Value("${app.configuration.default.motion.sensor.iamalive.interval.seconds}")
    private Long iAmAliveIntervalSeconds;

    @Value("${app.configuration.default.motion.sensor.iamalive.endpoint}")
    private String iAmAliveEndpoint;

    @Value("${app.configuration.default.motion.sensor.alert.endpoint}")
    private String alertEndpoint;


    private final WeatherSensorConfigurationRepository sensorConfigurationRepository;
    private final WeatherSensorConfigurationMapper sensorConfigurationMapper;
    private final DeviceRepository deviceRepository;
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;
    private final DeviceService deviceService;

    @Override
    public WeatherDTO insert(final WeatherDTO dto) {
        Weather model = this.weatherMapper.toModel(dto);
        Optional<Device> deviceOptional = this.deviceRepository.findByMacAddress(dto.getMacAddress());
        if (deviceOptional.isEmpty()) {
            this.deviceService.createNewDevice(dto.getMacAddress(), dto.getMacAddress());
            deviceOptional = this.deviceRepository.findByMacAddress(dto.getMacAddress());
        }
        model.setDevice(deviceOptional.get());
        Weather savedModel = this.weatherRepository.save(model);
        return this.weatherMapper.toDTO(savedModel);
    }

    @Override
    public List<WeatherDTO> getAllByDate(final String macAddress, final Date date) {
        Date startDate = DateUtil.calculateStartDate(date);
        Date endDate = DateUtil.calculateEndDate(date);
        List<Weather> weatherList = this.weatherRepository.findByCreatedDateBetweenAndDevice_macAddress(startDate, endDate, macAddress);
        return this.weatherMapper.toDTO(weatherList);
    }

    private List<WeatherDTO> getAllByDateInterval(final String macAddress, final Date dateStart, Date dateEnd) {
        List<Weather> weatherList = this.weatherRepository.findByCreatedDateBetweenAndDevice_macAddress(dateStart, dateEnd, macAddress);
        return this.weatherMapper.toDTO(weatherList);
    }

    @Override
    public WeatherDTO calculateAverageByDate(final String macAddress, final Date date) {
        List<WeatherDTO> dtoList = this.getAllByDate(macAddress, date);
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

    private static WeatherDTO calculateAverageFromList(List<WeatherDTO> dtoList) {
        double humidity = 0.0;
        double temperature = 0.0;
        double pressure = 0.0;
        int humidityCount = 0;
        int temperatureCount = 0;
        int pressureCount = 0;
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
        }
        WeatherDTO result = new WeatherDTO();
        result.setHumidity(humidity / humidityCount);
        result.setTemperature(temperature / temperatureCount);
        result.setPressure(pressure / pressureCount);
        return result;
    }

    @Override
    public WeatherDTO getLast(final String macAddress) {
        Weather weather = this.weatherRepository.findTopByDevice_MacAddressOrderByIdDesc(macAddress);
        return this.weatherMapper.toDTO(weather);
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
            this.deviceService.createNewDevice(sensorConfigurationRequestDTO.getMacAddress(), sensorConfigurationRequestDTO.getMacAddress());
            deviceOptional = this.deviceRepository.findByMacAddress(sensorConfigurationRequestDTO.getMacAddress());
        }
        WeatherSensorConfigurationDTO dto = loadDefaultConfiguration(sensorConfigurationRequestDTO.getMacAddress());
        WeatherSensorConfiguration model = sensorConfigurationMapper.toModel(dto);
        model.setDevice(deviceOptional.get());
        WeatherSensorConfigurationDTO result = this.sensorConfigurationMapper.toDTO(this.sensorConfigurationRepository.save(model));
        log.info(result.toString());
        return result;
    }

    private WeatherSensorConfigurationDTO loadDefaultConfiguration(String macAddress) {
        WeatherSensorConfigurationDTO dto = new WeatherSensorConfigurationDTO();
        dto.setAlertEndpoint(alertEndpoint);
        dto.setMacAddress(macAddress);
        dto.setIAmAliveIntervalSeconds(iAmAliveIntervalSeconds);
        dto.setIAmAliveEndpoint(iAmAliveEndpoint);
        dto.setWeatherSensorSupplyIntervalSeconds(weatherSensorSupplyIntervalSeconds);
        dto.setTemperatureSensorUnitOfMeasure(temperatureSensorUnitOfMeasure);
        return dto;
    }


}
