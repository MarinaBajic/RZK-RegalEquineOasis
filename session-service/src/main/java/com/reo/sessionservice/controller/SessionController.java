package com.reo.sessionservice.controller;

import com.reo.sessionservice.dto.SessionResponse;
import com.reo.sessionservice.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SessionResponse> getAllSessions() {
        return sessionService.getAllSessions();
    }
}
