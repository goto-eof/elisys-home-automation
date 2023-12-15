package com.andreidodu.elisyshomeautomation.model;

import com.andreidodu.elisyshomeautomation.model.common.ModelCommon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "ha_motion_detection")
@Getter
@Setter
public class MotionDetection extends ModelCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_timestamp", nullable = false)
    private Date lastTimestamp;

    @OneToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private Device device;
}
