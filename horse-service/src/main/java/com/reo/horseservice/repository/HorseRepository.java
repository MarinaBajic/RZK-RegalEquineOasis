package com.reo.horseservice.repository;

import com.reo.horseservice.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HorseRepository extends JpaRepository<Horse, Integer> {

    public Optional<Horse> findByFullName(String fullName);
}