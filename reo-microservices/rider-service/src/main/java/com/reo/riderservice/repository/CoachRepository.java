package com.reo.riderservice.repository;

import com.reo.riderservice.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Integer> {

    Optional<Coach> findByNameAndSurname(String name, String surname);
}