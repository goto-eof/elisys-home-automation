package com.andreidodu.elisyshomeautomation.resource.impl;

import com.andreidodu.elisyshomeautomation.resource.IAmAliveResource;
import com.andreidodu.elisyshomeautomation.dto.request.IAmAliveRequestDTO;
import com.andreidodu.elisyshomeautomation.service.IAmAliveService;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IAmAliveResourceImpl  implements IAmAliveResource {

    final private IAmAliveService iAmAliveService;

    public ResponseEntity<ResponseStatusDTO> check(@RequestBody IAmAliveRequestDTO iAmAliveRequestDTO) {
        return ResponseEntity.ok(iAmAliveService.update(iAmAliveRequestDTO));
    }

}
