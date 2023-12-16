package com.andreidodu.elisyshomeautomation.repository;

import com.andreidodu.elisyshomeautomation.entity.Device;
import com.andreidodu.elisyshomeautomation.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByMacAddress(String macAddress);

    List<Device> findByType(DeviceType type);
}