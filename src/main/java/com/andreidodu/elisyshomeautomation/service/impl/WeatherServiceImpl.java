package com.andreidodu.elisyshomeautomation.service.impl;

import com.andreidodu.elisyshomeautomation.dao.DeviceRepository;
import com.andreidodu.elisyshomeautomation.dao.WeatherRepository;
import com.andreidodu.elisyshomeautomation.dto.request.WeatherDTO;
import com.andreidodu.elisyshomeautomation.mapper.WeatherMapper;
import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.model.Weather;
import com.andreidodu.elisyshomeautomation.service.DeviceService;
import com.andreidodu.elisyshomeautomation.service.WeatherService;
import com.andreidodu.elisyshomeautomation.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final DeviceRepository deviceRepository;
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

    @Override
    public WeatherDTO calculateAverageByDate(final String macAddress, final Date date) {
        List<WeatherDTO> dtoList = this.getAllByDate(macAddress, date);
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


}
