package com.andreidodu.elisyshomeautomation.model;

import com.andreidodu.elisyshomeautomation.model.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ha_alarm_clock_configuration_cron")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AlarmClockConfigurationCron extends ModelCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cron", nullable = false)
    private String cron;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alarm_clock_configuration_id", referencedColumnName = "id")
    private AlarmClockConfiguration configuration;

    @Override
    public String toString() {
        return "AlarmClockConfigurationCron{" +
                "id=" + id +
                ", cron='" + cron + '\'' +
                ", description='" + description + '\'' +
                ", configuration=" + configuration +
                '}';
    }
}
