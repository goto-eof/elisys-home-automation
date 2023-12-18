package com.andreidodu.elisyshomeautomation.entity.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class ModelCommon {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedDate
    @Column(name = "insert_date", updatable = false, insertable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "update_date", insertable = false)
    protected LocalDateTime lastModifiedDate;

    @Version
    @Temporal(TemporalType.TIMESTAMP)
    protected Instant version;
}