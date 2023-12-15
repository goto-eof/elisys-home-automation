package com.andreidodu.elisyshomeautomation.model;

import com.andreidodu.elisyshomeautomation.model.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "ha_device")
@Getter
@Setter
public class Device extends ModelCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mac_address", nullable = false, unique = true)
    private String macAddress;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private Alive alive;

    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private AlarmClockConfiguration alarmClockConfiguration;

    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private WeatherSensorConfiguration weatherSensorConfiguration;

    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private MotionSensorConfiguration motionSensorConfiguration;

    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
    private RelayConfiguration relayConfiguration;

    @OneToMany(mappedBy = "device")
    private List<Weather> weatherList;

    @Enumerated(EnumType.STRING)
    private DeviceType type;
}
