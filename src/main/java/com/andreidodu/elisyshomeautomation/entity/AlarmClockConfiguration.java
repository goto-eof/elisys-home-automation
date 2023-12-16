package com.andreidodu.elisyshomeautomation.entity;

import com.andreidodu.elisyshomeautomation.entity.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ha_alarm_clock_configuration")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AlarmClockConfiguration extends ModelCommon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "timezone_seconds", nullable = false)
    private Long timezoneSeconds;

    @Column(name = "i_am_alive_endpoint", nullable = false)
    private String iAmAliveEndpoint;

    @Column(name = "i_am_alive_interval_seconds", nullable = false)
    private Long iAmAliveIntervalSeconds;

    @Column(name = "alarm_interval_minutes", nullable = false)
    private Integer alarmIntervalMinutes;

    @OneToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id", unique = true)
    private Device device;

    @OneToMany(mappedBy = "configuration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlarmClockConfigurationCron> cronList;

}
