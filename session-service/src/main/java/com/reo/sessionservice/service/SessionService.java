package com.reo.sessionservice.service;

import com.reo.sessionservice.dto.SessionRequest;
import com.reo.sessionservice.dto.SessionResponse;
import com.reo.sessionservice.exception.EntityDoesNotExistException;
import com.reo.sessionservice.exception.UnableToAddNewEntityException;
import com.reo.sessionservice.model.Horse;
import com.reo.sessionservice.model.Rider;
import com.reo.sessionservice.model.Session;
import com.reo.sessionservice.repository.HorseRepository;
import com.reo.sessionservice.repository.RiderRepository;
import com.reo.sessionservice.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private HorseRepository horseRepository;

    @Autowired
    private RiderRepository riderRepository;

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

    public void addNewSession(SessionRequest sessionRequest) {
        Optional<Horse> horseExists = horseRepository.findById(sessionRequest.getIdHorse());
        if (horseExists.isEmpty())
            throw new EntityDoesNotExistException("Horse with id: " + sessionRequest.getIdHorse() + " does not exist in the DB.", sessionRequest.getIdHorse());
        Horse horse = horseExists.get();

        Optional<Rider> riderExists = riderRepository.findById(sessionRequest.getIdRider());
        if (riderExists.isEmpty())
            throw new EntityDoesNotExistException("Rider with id: " + sessionRequest.getIdRider() + " does not exist in the DB.", sessionRequest.getIdRider());
        Rider rider = riderExists.get();

        Optional<Session> horseTaken = sessionRepository.findByHorseAndDateAndTime(horse, sessionRequest.getDate(), sessionRequest.getTime());
        if (horseTaken.isPresent())
            throw new UnableToAddNewEntityException("Horse with id: " + sessionRequest.getIdHorse() + " is not available for selected date and time. Please choose another.");

        Session session = Session.builder()
                .date(sessionRequest.getDate())
                .description(sessionRequest.getDescription())
                .time(sessionRequest.getTime())
                .horse(horse)
                .rider(rider)
                .build();

        sessionRepository.save(session);
        log.info("Session with id: {} is saved", session.getIdSession());
    }

    public void deleteSession(int idSession) {
        Optional<Session> sessionOptional = sessionRepository.findById(idSession);
        if (sessionOptional.isEmpty())
            throw new EntityDoesNotExistException("Session with id: " + idSession + " does not exist in the DB.", idSession);

        Session session = sessionOptional.get();
        sessionRepository.delete(session);
        log.info("Session with id: {} successfully deleted.", idSession);
    }
}
