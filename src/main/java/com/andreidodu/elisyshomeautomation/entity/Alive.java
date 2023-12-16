package com.andreidodu.elisyshomeautomation.entity;

import com.andreidodu.elisyshomeautomation.entity.common.ModelCommon;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ha_alive")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Alive extends ModelCommon implements Serializable {

    @Column(name = "last_ack_timestamp", nullable = false)
    private LocalDateTime lastAckTimestamp;

    @OneToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id", unique = true)
    private Device device;

}
