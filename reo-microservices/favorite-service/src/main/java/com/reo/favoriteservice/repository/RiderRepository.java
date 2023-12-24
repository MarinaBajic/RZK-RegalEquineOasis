package com.reo.favoriteservice.repository;

import com.reo.favoriteservice.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Integer> {
}