package com.andreidodu.elisyshomeautomation.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class ModelCommon {

    @CreatedDate
    @Getter
    @Column(name = "insert_date", updatable = false, insertable = false)
    protected Instant createdDate;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "update_date", insertable = false)
    protected Instant lastModifiedDate;

    @Version
    @Column(name = "version")
    private int version;
}