package com.andreidodu.elisyshomeautomation.entity.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class ModelCommon implements Serializable {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedDate
    @Getter
    @Column(name = "insert_date", updatable = false, insertable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Getter
    @Column(name = "update_date", insertable = false)
    protected LocalDateTime lastModifiedDate;

    @Version
    private Integer version;
}