package com.andreidodu.elisyshomeautomation.entity;

import com.andreidodu.elisyshomeautomation.entity.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "ha_relay_configuration")
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
public class RelayConfiguration extends ModelCommon implements Serializable {

    private Boolean powerOn;

    @OneToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id", nullable = false)
    private Device device;
}
