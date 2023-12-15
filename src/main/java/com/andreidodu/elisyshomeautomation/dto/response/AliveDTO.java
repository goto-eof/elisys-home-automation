package com.andreidodu.elisyshomeautomation.dto.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class AliveDTO {
    private Long id;
    private String macAddress;
    private LocalDateTime lastAckTimestamp;
}
