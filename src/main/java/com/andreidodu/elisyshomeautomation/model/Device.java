package com.andreidodu.elisyshomeautomation.model;

import com.andreidodu.elisyshomeautomation.model.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "ha_device")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Device extends ModelCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mac_address", nullable = false, unique = true)
    private String macAddress;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(mappedBy = "device")
    private Alive alive;

    @OneToOne(mappedBy = "device")
    private MotionSensorConfiguration motionSensorConfiguration;

    @OneToOne(mappedBy = "device")
    private WeatherSensorConfiguration weatherSensorConfiguration;

    @OneToMany(mappedBy = "device")
    private List<Weather> weather;
}
