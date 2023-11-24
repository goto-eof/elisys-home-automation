package com.andreidodu.elisyshomeautomation.controller;

import com.andreidodu.elisyshomeautomation.dto.request.IAmAliveRequestDTO;
import com.andreidodu.elisyshomeautomation.dto.response.ResponseStatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/i-am-alive")
public interface IAmAliveResource {
    @Operation(summary = "Notify to server that the device is online and is enabled")
    @PostMapping(value = "/notify")
    ResponseEntity<ResponseStatusDTO> check(@RequestBody IAmAliveRequestDTO iAmAliveRequestDTO);
}
