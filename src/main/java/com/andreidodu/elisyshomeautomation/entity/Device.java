package com.andreidodu.elisyshomeautomation.entity;

import com.andreidodu.elisyshomeautomation.entity.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "ha_device")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Device extends ModelCommon implements Serializable {

    @Column(name = "mac_address", nullable = false, unique = true)
    private String macAddress;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private Alive alive;
//
//    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = true)
//    private AlarmClockConfiguration alarmClockConfiguration;
//
//    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = true)
//    private WeatherSensorConfiguration weatherSensorConfiguration;
//
//    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = true)
//    private MotionSensorConfiguration motionSensorConfiguration;
//
//    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = true)
//    private RelayConfiguration relayConfiguration;
//
//    @OneToMany(mappedBy = "device")
//    private List<Weather> weatherList;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DeviceType type;
}
