package com.backend.controller;

import com.backend.dto.Response;
import com.backend.entity.RefCode;
import com.backend.entity.Transport;
import com.backend.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transport")
@RequiredArgsConstructor
public class TransportController {

    @Autowired
    TransportService transportService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Transport transport) {
        Transport savedTransport = null;
        try{
            savedTransport = transportService.bookTransport(transport);
        }catch(Exception e){
            Response res = Response.builder().status(500).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
        Response res = Response.builder()
                .status(200)
                .message("Transpost is booked successfully.")
                .transport(savedTransport)
                .build();
        return ResponseEntity.ok(res);
    }

}
