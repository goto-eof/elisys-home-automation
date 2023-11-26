package com.andreidodu.elisyshomeautomation.dao;

import com.andreidodu.elisyshomeautomation.model.Alive;
import com.andreidodu.elisyshomeautomation.model.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, Long> {
    Optional<Device> findByMacAddress(String macAddress);
}