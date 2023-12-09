package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.model.Device;
import com.andreidodu.elisyshomeautomation.model.DeviceType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, Long> {
    Optional<Device> findByMacAddress(String macAddress);

    List<Device> findByType(DeviceType type);
}