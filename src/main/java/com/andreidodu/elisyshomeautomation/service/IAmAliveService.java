package com.andreidodu.elisyshomeautomation.service;

import com.andreidodu.elisyshomeautomation.dto.request.IAmAliveRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;

public interface IAmAliveService {
    ResponseStatusDTO check(final IAmAliveRequestDTO iAmAliveRequestDTO);
}
