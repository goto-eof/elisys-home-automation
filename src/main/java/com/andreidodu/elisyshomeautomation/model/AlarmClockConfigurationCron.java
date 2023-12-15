package com.andreidodu.elisyshomeautomation.model;

import com.andreidodu.elisyshomeautomation.model.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ha_alarm_clock_configuration_cron")
@Getter
@Setter
public class AlarmClockConfigurationCron extends ModelCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cron", nullable = false)
    private String cron;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "alarm_clock_configuration_id", referencedColumnName = "id")
    private AlarmClockConfiguration configuration;

}
