package com.andreidodu.elisyshomeautomation.model;

import com.andreidodu.elisyshomeautomation.model.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "ha_sensor_configuration")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class SensorConfiguration extends ModelCommon {

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

    private String crontab;

    @OneToOne(cascade = CascadeType.ALL)
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
