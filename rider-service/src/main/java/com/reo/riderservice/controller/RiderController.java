package com.reo.riderservice.controller;

import com.reo.riderservice.dto.RiderResponse;
import com.reo.riderservice.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rider")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RiderResponse> getAllRiders() {
        return riderService.getAllRiders();
    }
}
