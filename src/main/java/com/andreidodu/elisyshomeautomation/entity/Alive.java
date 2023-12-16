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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_ack_timestamp", nullable = false)
    private LocalDateTime lastAckTimestamp;

    @OneToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id", unique = true)
    private Device device;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alive alive = (Alive) o;
        return Objects.equals(id, alive.id) && Objects.equals(lastAckTimestamp, alive.lastAckTimestamp) && Objects.equals(device, alive.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastAckTimestamp, device);
    }
}
