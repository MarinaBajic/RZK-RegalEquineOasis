package com.reo.horseservice.repository;

import com.reo.horseservice.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorseRepository extends JpaRepository<Horse, Integer> {
}