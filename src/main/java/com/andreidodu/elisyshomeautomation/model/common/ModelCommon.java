package com.andreidodu.elisyshomeautomation.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
public class ModelCommon {

    @Getter
    @CreatedDate
    @Column(name = "insert_date", updatable = false, insertable = false)
    protected Date createdDate;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "update_date", updatable = true, insertable = false)
    protected Date lastModifiedDate;

    @Version
    private int version;
}