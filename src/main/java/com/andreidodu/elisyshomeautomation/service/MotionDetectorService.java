package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.request.AlertRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;

public interface MotionDetectorService {
    ResponseStatusDTO alert(final AlertRequestDTO alertRequestDTO);
}
