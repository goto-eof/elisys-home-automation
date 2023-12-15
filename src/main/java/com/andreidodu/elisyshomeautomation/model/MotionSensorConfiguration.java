package com.andreidodu.elisyshomeautomation.model;

import com.andreidodu.elisyshomeautomation.model.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ha_motion_sensor_configuration")
@Getter
@Setter
public class MotionSensorConfiguration extends ModelCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "alert_endpoint", nullable = false)
    private String alertEndpoint;

    @Column(name = "i_am_alive_endpoint", nullable = false)
    private String iAmAliveEndpoint;

    @Column(name = "i_am_alive_interval_seconds", nullable = false)
    private Long iAmAliveIntervalSeconds;

    @Column(name = "timezone_offset_sec", nullable = false)
    private Integer timezoneOffsetSec;

    private String crontab;

    @OneToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private Device device;

    @Override
    public String toString() {
        return "SensorConfiguration{" +
                "id=" + id +
                ", macAddress='" + device.getMacAddress() + '\'' +
                ", alertEndpoint='" + alertEndpoint + '\'' +
                ", iAmAliveEndpoint='" + iAmAliveEndpoint + '\'' +
                ", iAmAliveIntervalSeconds=" + iAmAliveIntervalSeconds +
                ", crontab='" + crontab + '\'' +
                '}';
    }
}
