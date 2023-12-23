package com.reo.sessionservice.service;

import com.reo.sessionservice.dto.SessionResponse;
import com.reo.sessionservice.model.Session;
import com.reo.sessionservice.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public List<SessionResponse> getAllSessions() {
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream().map(this::mapToSessionResponse).toList();
    }

    private SessionResponse mapToSessionResponse(Session session) {
        return SessionResponse.builder()
                .idSession(session.getIdSession())
                .date(session.getDate())
                .description(session.getDescription())
                .time(session.getTime())
                .horse(session.getHorse())
                .rider(session.getRider())
                .build();
    }
}
