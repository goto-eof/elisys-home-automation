package com.andreidodu.elisyshomeautomation.entity;

import com.andreidodu.elisyshomeautomation.entity.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "ha_alarm_clock_configuration_cron")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AlarmClockConfigurationCron extends ModelCommon implements Serializable {

    @Column(name = "cron", nullable = false)
    private String cron;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "alarm_clock_configuration_id", referencedColumnName = "id")
    private AlarmClockConfiguration configuration;

}
