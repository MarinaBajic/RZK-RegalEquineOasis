package com.reo.sessionservice.repository;

import com.reo.sessionservice.model.Horse;
import com.reo.sessionservice.model.Rider;
import com.reo.sessionservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Integer> {

    Optional<Session> findByHorseAndDateAndTime(Horse horse, Date date, String time);
    List<Session> findAllByRider(Rider rider);
    List<Session> findAllByHorse(Horse horse);
}