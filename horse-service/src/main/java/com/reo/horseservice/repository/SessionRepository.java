package com.reo.horseservice.repository;

import com.reo.horseservice.model.Horse;
import com.reo.horseservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {

    public List<Session> findAllByHorse(Horse horse);
}