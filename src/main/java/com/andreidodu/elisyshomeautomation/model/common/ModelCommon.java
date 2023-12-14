package com.andreidodu.elisyshomeautomation.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
public abstract class ModelCommon {

    @CreatedDate
    @Getter
    @Column(name = "insert_date", updatable = false, insertable = false)
    protected Date createdDate;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "update_date", insertable = false)
    protected Date lastModifiedDate;

    @Version
    @Column(name = "version")
    private int version;
}