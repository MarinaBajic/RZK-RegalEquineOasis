package com.reo.sessionservice.controller;

import com.reo.sessionservice.dto.SessionRequest;
import com.reo.sessionservice.dto.SessionResponse;
import com.reo.sessionservice.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/rider")
    @ResponseStatus(HttpStatus.OK)
    public List<SessionResponse> getAllSessionsForRider(@RequestParam("id-rider") int idRider) {
        return sessionService.getAllSessionsForRider(idRider);
    }

    @GetMapping("/horse")
    @ResponseStatus(HttpStatus.OK)
    public List<SessionResponse> getAllSessionsForHorse(@RequestParam("id-horse") int idHorse) {
        return sessionService.getAllSessionsForHorse(idHorse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewSession(@RequestBody SessionRequest sessionRequest) {
        sessionService.addNewSession(sessionRequest);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSession(@RequestParam("id-session") int idSession) {
        sessionService.deleteSession(idSession);
    }
}
