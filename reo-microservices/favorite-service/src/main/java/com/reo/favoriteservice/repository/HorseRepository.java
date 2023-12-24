package com.reo.favoriteservice.repository;

import com.reo.favoriteservice.model.Horse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorseRepository extends JpaRepository<Horse, Integer> {
}