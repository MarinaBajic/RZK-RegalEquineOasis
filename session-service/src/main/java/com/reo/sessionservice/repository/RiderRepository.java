package com.reo.sessionservice.repository;

import com.reo.sessionservice.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Integer> {
}