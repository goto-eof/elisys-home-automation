package com.andreidodu.elisyshomeautomation.model;

import com.andreidodu.elisyshomeautomation.model.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ha_alive")
@Getter
@Setter
public class Alive extends ModelCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_ack_timestamp", nullable = false)
    private LocalDateTime lastAckTimestamp;

    @OneToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private Device device;
}
