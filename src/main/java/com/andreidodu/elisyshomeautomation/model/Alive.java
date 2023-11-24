package com.andreidodu.elisyshomeautomation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "ha_alive")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Alive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mac_address", nullable = false, unique = true)
    private String macAddress;

    @Column(name = "last_ack_timestamp", nullable = false)
    private Date lastAckTimestamp;
}
