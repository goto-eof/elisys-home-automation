package com.andreidodu.elisyshomeautomation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AlarmClockConfigurationCronResponseDTO {
    private Long id;
    private String cron;
    private String description;

    public AlarmClockConfigurationCronResponseDTO(final String cron, final String description) {
        super();
        this.cron = cron;
        this.description = description;
    }
}
