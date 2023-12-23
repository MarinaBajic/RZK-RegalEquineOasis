package com.reo.sessionservice.repository;

import com.reo.sessionservice.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorseRepository extends JpaRepository<Horse, Integer> {
}